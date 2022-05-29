<template>
  <el-table :data="personalData" stripe style="width: 100%">
    <el-table-column prop="personalId" label="身份证号" />
    <el-table-column prop="balance" label="余额" />
    <el-table-column prop="loan" label="贷款额" />
    <el-table-column prop="ceiling" label="信用额" />
  </el-table>
  <span class="page">{{ this.page }}/{{ this.pages }}</span>
  <el-form>
    <el-form-item style="padding-top: 10px">
      <el-button @click="backward()">上一页</el-button>
      <el-button type="primary" @click="forward()">下一页</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import axios from "axios";
export default {
  name: "RankView",
  data() {
    return {
      pages: 1,
      page: 1,
      personalData: [],
    };
  },
  mounted() {
    axios.defaults.baseURL = "/api/admin/rank";
    axios.defaults.withCredentials = true;
    this.getAllData();
  },
  methods: {
    getAllData() {
      axios({
        method: "get",
        url: `/${this.page}/10`,
      })
        .then((res) => {
          if (res.data.code == "00000") {
            this.personalData = res.data.result.records;
            this.pages = res.data.result.pages;
          } else {
            this.$message.error(res.data.message);
          }
        })
        .catch((err) => {
          this.$message.error("服务器连接失败");
          console.log(err);
        });
    },
    forward() {
      if (this.page < this.pages) {
        this.page++;
        this.getAllData();
      } else {
        this.$message.error("已经是最后一页了");
      }
    },
    backward() {
      if (this.page > 1) {
        this.page--;
        this.getAllData();
      } else {
        this.$message.error("已经是第一页了");
      }
    },
  },
};
</script>

<style>
.page {
  padding-top: 15px;
  float: right;
}
</style>
