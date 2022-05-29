<template>
  <el-row class="mb-4">
    <el-button type="primary" @click="this.$router.push('/login')">登录</el-button>
  </el-row>
  <el-row class="mb-4">
    <el-button @click="this.$router.push('/register')">注册</el-button>
  </el-row>
</template>

<script>
export default {
    name: 'MainView',
}
</script>

<style>
.el-row {
  padding-top: 10px;
  display: flex;
  justify-content: center;
}
</style>