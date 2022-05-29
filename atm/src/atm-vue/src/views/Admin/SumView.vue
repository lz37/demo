<template>
  <el-form
    :model="sumData"
    ref="sumData"
    label-width="120px"
    label-position="left"
  >
    <el-form-item label="总 余 额" prop="balance">
      <el-input v-model="sumData.balance" disabled />
    </el-form-item>
    <el-form-item label="总 信 用 额 度" prop="ceiling">
      <el-input v-model="sumData.ceiling" disabled />
    </el-form-item>
    <el-form-item label="总 贷 款" prop="loan">
      <el-input v-model="sumData.loan" disabled />
    </el-form-item>
  </el-form>
</template>

<script>
import axios from "axios";
export default {
  name: "SumView",
  data() {
    return {
      sumData: {
        balance: "",
        ceiling: "",
        loan: "",
      },
    };
  },
  mounted() {
    axios.defaults.baseURL = "/api/admin/sum";
    axios.defaults.withCredentials = true;
    this.getAllData();
  },
  methods: {
    getAllData() {
      for (let key in this.sumData) {
        this.getData(key);
      }
    },
    getData(data) {
      axios({
        method: "get",
        url: `/${data}`,
      })
        .then((response) => {
          if (response.data.code == "00000") {
            this.sumData[data] = response.data.result;
          } else {
            this.$message.error(response.data.message);
          }
        })
        .catch((error) => {
          this.$message.error("服务器连接失败");
          console.log(error);
        });
    },
  },
};
</script>

<style></style>
