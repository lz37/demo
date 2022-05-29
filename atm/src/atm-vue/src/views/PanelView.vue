<template>
  <el-container>
    <el-container>
      <el-aside width="200px" v-if="this.isAdmin">
        <el-menu router>
          <el-menu-item index="/business">
            <span>普通用户面板</span>
          </el-menu-item>
          <el-sub-menu index="admin">
            <template #title>
              <span>管理员面板</span>
            </template>
            <el-menu-item index="/business/ceiling">
              <span>信用额度设置</span>
            </el-menu-item>
            <el-menu-item index="/business/sum">
              <span>统计面板</span>
            </el-menu-item>
            <el-menu-item index="/business/rank">
              <span>排名面板</span>
            </el-menu-item>
          </el-sub-menu>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import axios from "axios";
export default {
  name: "PanelView",
  data() {
    return {
      isAdmin: this.getIsAdmin,
    };
  },
  mounted() {
    this.getIsAdmin();
  },
  methods: {
    getIsAdmin() {
      axios({
        method: "get",
        baseURL: "/api",
        url: "/admin/isadmin",
      })
        .then((res) => {
          if (res.data.code == "00000") {
            this.isAdmin = res.data.result;
          } else {
            this.$message.error(res.data.message);
            this.isAdmin = false;
          }
        })
        .catch((err) => {
          this.$message.error("服务器连接失败");
          console.error(err);
          this.isAdmin = false;
        });
    },
  },
};
</script>

<style></style>
