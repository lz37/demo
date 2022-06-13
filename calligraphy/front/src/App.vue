<template>
  <div class="common-layout">
    <el-header>
      <div class="personal-center">
        <el-avatar :icon="UserFilled" />
      </div>
      <h2>中小学生书法学习智能评价系统</h2>
    </el-header>
    <router-view />
    <el-footer> Developed by<br />廖伟，蒋建超，惠超，谢泽凯 </el-footer>
  </div>
</template>

<script>
import { UserFilled } from '@element-plus/icons-vue'
export default {
  name: "App",
  data() {
    return {
      UserFilled: UserFilled,
    }
  },
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
.personal-center {
  float: right;
  padding-top: 10px;
}
.el-header {
  text-align: center;
  background-color: #1d1626;
  color: #fff;
}
.el-footer {
  width: 100%;
  text-align: center;
  background-color: #1d1626;
  color: #fff;
  position: absolute;
  bottom: 0;
}
</style>
