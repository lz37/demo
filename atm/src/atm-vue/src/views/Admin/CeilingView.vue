<template>
  <el-form
    :model="ceilingInfo"
    ref="ceilingInfo"
    :rules="ceilingRules"
    label-width="120px"
    label-position="left"
  >
    <el-form-item label="账 户 ID" prop="id">
      <el-input v-model="ceilingInfo.id" />
    </el-form-item>
    <el-form-item label="信 用 金 额" prop="ceiling">
      <el-input v-model="ceilingInfo.ceiling" />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submit()">提交</el-button>
      <el-button @click="reset()">清空</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import axios from "axios";
export default {
  name: "CeilingView",
  data() {
    return {
      ceilingInfo: {
        id: "",
        ceiling: "",
      },
      ceilingRules: {
        id: [{ required: true, message: "请输入账户ID", trigger: "blur" }],
        ceiling: [
          { required: true, message: "请输入信用额度", trigger: "blur" },
          {
            validator: (rule, value, callback) => {
              if (isNaN(Number(value))) {
                callback(new Error("信用额度必须为数字"));
              } else if (Number(value) < 0) {
                callback(new Error("信用额度不能为负数"));
              } else {
                callback();
              }
            },
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    reset() {
      this.ceilingInfo = {
        id: "",
        ceiling: "",
      };
    },
    submit() {
      axios({
        method: "post",
        baseURL: "/api/admin",
        url: `/ceiling/${this.ceilingInfo.id}/${this.ceilingInfo.ceiling}`,
        withCredentials: true,
      })
        .then((res) => {
          if (res.data.code == "00000") {
            this.$message({
              type: "success",
              message: "操作成功",
            });
            this.reset();
          } else {
            this.$message({
              type: "error",
              message: res.data.message,
            });
          }
        })
        .catch((err) => {
          this.$message({
            type: "error",
            message: err.message,
          });
          console.error(err);
        });
    },
  },
};
</script>

<style></style>
