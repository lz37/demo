<template>
  <el-header>
    <h2>模拟 ICBC ATM 终端</h2>
  </el-header>
  <div class="container">
    <router-view />
  </div>
  <el-footer>Developed by<br />Zerozawa</el-footer>
</template>

<script>
export default {
  name: "App",
};
</script>

<style>
* {
  margin: 0;
  padding: 0;
}
html,
body,
#app {
  height: 100%;
}
.el-header {
  text-align: center;
  background-color: #409eff;
  color: #fff;
}
.container {
  padding-top: 20px;
  width: 80%;
  margin: 0 auto;
  height: 80%;
}
.el-footer {
  width: 100%;
  text-align: center;
  background-color: #409eff;
  color: #fff;
  position: absolute;
  bottom: 0;
}
@media screen and (min-width: 768px) {
  .container {
    width: 614px;
  }
}
</style>
