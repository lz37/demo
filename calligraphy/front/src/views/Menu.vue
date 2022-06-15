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
          <el-menu-item
            index="1"
            @click="this.$router.push('/menu')"
            v-if="isStudent"
            >书法课程</el-menu-item
          >
          <el-menu-item index="2" @click="this.$router.push('/menu/copybook')"
            >系统字帖</el-menu-item
          >
          <el-menu-item
            index="3"
            @click="this.$router.push('/menu/collection')"
            v-if="isStudent"
            >我的收藏</el-menu-item
          >
          <el-menu-item
            index="4"
            @click="this.$router.push('/menu/misprint')"
            v-if="isStudent"
            >错字重练</el-menu-item
          >
          <el-menu-item
            index="5"
            @click="this.$router.push('/menu/homework')"
            v-if="isStudent"
            >我的作业</el-menu-item
          >
          <el-menu-item
            index="6"
            v-if="isTeacher || isAdmin"
            @click="this.$router.push('/menu/course-manager')"
            >课程管理</el-menu-item
          >
          <el-menu-item
            index="7"
            @click="this.$router.push('/menu/announcement')"
            >公告</el-menu-item
          >
          <el-menu-item
            index="8"
            @click="this.$router.push('/menu/school')"
            v-if="isAdmin"
            >学校管理</el-menu-item
          >
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
  name: "MenuView",
  data() {
    return {
      UserFilled: UserFilled,
      userName: "NULL",
      isTeacher: window.localStorage.getItem("identity") == "teacher",
      isStudent: window.localStorage.getItem("identity") == "student",
      isAdmin: window.localStorage.getItem("identity") == "admin",
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
  width: 100%;
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
  margin-bottom: 50px;
}
</style>
