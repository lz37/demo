<template>
  <el-form
    :model="businessInfo"
    ref="businessInfo"
    label-width="120px"
    label-position="left"
  >
    <el-form-item label="账 户 ID" prop="id">
      <el-input v-model="businessInfo.id" disabled />
    </el-form-item>
    <el-form-item label="姓 名" prop="name">
      <el-input v-model="businessInfo.name" disabled />
    </el-form-item>
    <el-form-item label="余 额" prop="balance">
      <el-input v-model="businessInfo.balance" disabled />
    </el-form-item>
    <el-form-item
      label="信 用 额 度"
      prop="ceiling"
      v-if="!isNaN(businessInfo.ceiling)"
    >
      <el-input v-model="businessInfo.ceiling" disabled />
    </el-form-item>
    <el-form-item label="贷 款 额" prop="loan" v-if="!isNaN(businessInfo.loan)">
      <el-input v-model="businessInfo.loan" disabled />
    </el-form-item>
  </el-form>

  <el-form
    :model="request"
    ref="request"
    label-width="0px"
    :rules="requestRules"
    @submit.enter.prevent
    status-icon
    :validate-on-rule-change="false"
  >
    <el-form-item prop="money">
      <el-input v-model="request.money" placeholder="请输入金额">
        <template #prepend>
          <el-form-item prop="type">
            <el-select
              v-model="request.type"
              placeholder="操作"
              style="width: 120px"
            >
              <el-option label="存款" value="post balance" />
              <el-option label="取款" value="delete balance" />
              <el-option
                label="贷款"
                value="post loan"
                v-if="!isNaN(businessInfo.loan)"
              />
              <el-option
                label="还贷"
                value="delete loan"
                v-if="!isNaN(businessInfo.loan)"
              />
            </el-select>
          </el-form-item>
        </template>
      </el-input>
    </el-form-item>
    <el-form-item label-width="120px">
      <el-button type="primary" @click="push('request')">确定</el-button>
      <el-button @click="this.$router.push('/')">返回</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import axios from "axios";
export default {
  name: "BusinessView",
  data() {
    return {
      businessInfo: {
        balance: NaN,
        ceiling: NaN,
        email: "",
        id: NaN,
        loan: NaN,
        name: "",
      },
      request: {
        money: "",
        type: "",
      },
      requestRules: {
        money: [
          { required: true, message: "请输入金额", trigger: "blur" },
          {
            validator: (rule, value, callback) => {
              if (Number(value) < 0) {
                callback(new Error("金额不能为负数"));
              } else {
                callback();
              }
            },
            trigger: "blur",
          },
          {
            validator: (rule, value, callback) => {
              if (!Number(value)) {
                callback(new Error("金额必须为数字"));
              } else {
                callback();
              }
            },
            trigger: "blur",
          },
        ],
        type: [{ required: true, message: "请选择操作", trigger: "change" }],
      },
    };
  },
  mounted() {
    axios.defaults.baseURL = "/api";
    this.getAccount();
  },
  methods: {
    getAccount() {
      axios({
        method: "get",
        url: "/account",
      })
        .then((res) => {
          for (let key in res.data.result) {
            if (res.data.result[key] != null) {
              this.businessInfo[key] = res.data.result[key];
            }
          }
        })
        .catch((err) => {
          this.$message.error("服务器连接失败");
          console.log(err);
        });
    },
    push(formName) {
      this.$refs[formName].validate((valid) => {
        if (!valid) {
          this.$message.error("请检查输入");
          return false;
        } else {
          let method = this.request.type.split(" ")[0];
          let api = this.request.type.split(" ")[1];
          axios({
            method: method,
            url: `/${api}/${this.request.money}`,
          })
            .then((res) => {
              if (res.data.code == "00000") {
                this.$message.success("操作成功");
                this.getAccount();
                this.request.money = "";
              } else {
                this.$message.error(res.data.message);
              }
            })
            .catch((err) => {
              this.$message.error("服务器连接失败");
              console.log(err);
            });
        }
      });
    },
  },
};
</script>

<style></style>
