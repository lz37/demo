<template>
  <el-container>
    <el-aside width="150px">
      <el-scrollbar>
        <el-avatar
          class="personal-img-aside"
          :icon="UserFilled"
          shape="square"
          :size="100"
        />
        <h1 class="personal-name">{{ userName }}</h1>
        <el-menu>
          <el-menu-item index="1" @click="this.$router.push('/student-menu')">
            书法课程
          </el-menu-item>
          <el-menu-item index="2">系统字帖</el-menu-item>
          <el-menu-item index="3">我的收藏</el-menu-item>
          <el-menu-item index="4">错字重练</el-menu-item>
          <el-menu-item index="5">我的作业</el-menu-item>
          <el-menu-item index="6">讨论中心</el-menu-item>
        </el-menu>
      </el-scrollbar>
    </el-aside>
    <el-main>
      <router-view />
    </el-main>
  </el-container>
</template>

<script>
import { UserFilled } from "@element-plus/icons-vue";
export default {
  name: "StudentMenuView",
  data() {
    return {
      UserFilled: UserFilled,
      userName: "NULL",
    };
  },
};
</script>

<style>
.el-aside {
  height: 100%;
}
.el-container {
  height: 100%;
  position: fixed;
}
.el-menu-item {
  align-items: center;
}
.personal-img-aside {
  margin: 25px;
}
.personal-name {
  margin-left: 45px;
}
</style>
