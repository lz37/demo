<template>
  <div class="login-container">
    <el-form :model="form" label-width="120px">
      <img :src="slogan" />
      <el-form-item label=" 用 户 名 ">
        <el-input v-model="form.name" clearable />
      </el-form-item>
      <el-form-item label=" 密 码 ">
        <el-input
          v-model="form.password"
          type="password"
          show-password
          clearable
        />
      </el-form-item>
      <el-form-item>
        <el-button color="#1d1626" dark="isDark" @click="this.$router.push('/student-menu')">登录</el-button>
        <el-button @click="this.$router.push('/signup')">注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import slogan from "../assets/slogan.png";
import loginBack from "../assets/login.jpeg";
export default {
  name: "LoginView",
  data() {
    return {
      form: {
        name: "",
        password: "",
      },
      slogan: slogan,
      loginBack: loginBack,
    };
  },
};
</script>

<style>
.login-container {
  width: 100%;
  height: 80%;
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
}
</style>
