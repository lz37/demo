<template>
  <el-form
    ref="registerForm"
    :model="registerForm"
    label-width="120px"
    :rules="rules"
    @submit.enter.prevent
    status-icon
    :validate-on-rule-change="false"
  >
    <el-form-item label="账户类型" prop="accountType">
      <el-select
        v-model="registerForm.accountType"
        class="m-2"
        placeholder="选择你的账户类型"
        size="large"
      >
        <el-option
          v-for="accountType in accountTypes"
          :key="accountType.key"
          :label="accountType.label"
          :value="accountType.key"
          :disabled="accountType.disabled"
        />
      </el-select>
    </el-form-item>
    <el-form-item label="用 户 名" prop="name">
      <el-input v-model="registerForm.name" clearable />
    </el-form-item>
    <el-form-item label="密 码" prop="password">
      <el-input
        v-model="registerForm.password"
        type="password"
        clearable
        show-password
      />
    </el-form-item>
    <el-form-item label="确 认 密 码" prop="confirmPassword">
      <el-input
        v-model="registerForm.confirmPassword"
        type="password"
        clearable
        show-password
      />
    </el-form-item>
    <el-form-item label="身 份 证 号" prop="personalId">
      <el-input v-model="registerForm.personalId" clearable />
    </el-form-item>
    <el-form-item label="E-mail" prop="email">
      <el-input v-model="registerForm.email" clearable />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('registerForm')"
        >提交</el-button
      >
      <el-button @click="this.$router.push('/')">返回</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import { reactive } from "vue";
import axios from "axios";
export default {
  name: "RegisterView",
  data() {
    return {
      registerForm: reactive({
        accountType: "",
        name: "",
        password: "",
        confirmPassword: "",
        personalId: "",
        email: "",
      }),
      accountTypes: [],
      rules: {
        accountType: [
          { required: true, message: "请选择你的账户类型", trigger: "change" },
        ],
        name: [
          { required: true, message: "请输入你的用户名", trigger: "blur" },
        ],
        password: [
          { required: true, message: "请输入你的密码", trigger: "blur" },
        ],
        confirmPassword: [
          { required: true, message: "请输入你的确认密码", trigger: "blur" },
          {
            validator: this.validateConfirmPassword,
            trigger: "blur",
          },
        ],
        personalId: [
          { required: true, message: "请输入你的身份证号", trigger: "blur" },
        ],
        email: [
          { required: true, message: "请输入你的E-mail", trigger: "blur" },
          { type: "email", message: "请输入正确的E-mail", trigger: "blur" },
        ],
      },
    };
  },
  mounted: function () {
    axios.defaults.baseURL="/api";
    this.getAllAccountType();
  },
  methods: {
    getAllAccountType() {
      axios({
        method: "get",
        url: "/sum/account-type",
      }).then((res) => {
        this.accountTypes = res.data.result;
        for (let i = 0; i < this.accountTypes.length; i++) {
          if (this.accountTypes[i].key == "Admin") {
            this.accountTypes[i].disabled = true;
          } else {
            this.accountTypes[i].disabled = false;
          }
        }
      });
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios({
            method: "put",
            url: "/account",
            data: this.registerForm,
          })
            .then((res) => {
              if (res.data.code == "00000") {
                this.$message({
                  message: "注册成功<br />你的账号是:  " + res.data.result.id,
                  type: "success",
                  dangerouslyUseHTMLString: true,
                });
                this.$router.push("/login");
              } else {
                this.$message.error(res.data.message);
              }
            })
            .catch((err) => {
              this.$message.error("服务器连接失败");
              console.error(err);
            });
        } else {
          this.$message.error("请检查输入");
          return false;
        }
      });
    },
    validateConfirmPassword(rule, value, callback) {
      if (value === this.registerForm.password) {
        callback();
      } else {
        callback(new Error("两次输入的密码不一致"));
      }
    },
  },
};
</script>

<style></style>
