<template>
  <el-form
    :model="loginForm"
    label-width="80px"
    :rules="rules"
    ref="loginForm"
    @submit.enter.prevent
    status-icon
    :validate-on-rule-change="false"
  >
    <el-form-item label="账 号" prop="id">
      <el-input v-model="loginForm.id" clearable />
    </el-form-item>
    <el-form-item label="密 码" prop="password">
      <el-input
        v-model="loginForm.password"
        show-password
        clearable
        autocomplete="off"
      />
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm('loginForm')"
        >登录</el-button
      >
      <el-button @click="this.$router.push('/')">返回</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import { reactive } from "vue";
import axios from "axios";
export default {
  name: "LoginView",
  data() {
    return {
      loginForm: reactive({
        id: "",
        password: "",
      }),
      rules: {
        id: [
          { required: true, message: "请输入账号", trigger: "blur" },
          { validator: this.validateId, trigger: "blur" },
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }],
      },
    };
  },
  methods: {
    validateId(rule, value, callback) {
      if (!Number(value)) {
        callback(new Error("账号格式错误"));
      }
      callback(); // 一定要加，否则submitForm中valid为true不执行
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          axios({
            method: "post",
            baseURL: "/api/",
            url: "/account",
            data: this.loginForm,
          })
            .then((res) => {
              if (res.data.code == "00000") {
                this.$message.success("登陆成功");
                // console.log(document.cookie);
                this.$router.push("/business");
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
  },
};
</script>

<style></style>
