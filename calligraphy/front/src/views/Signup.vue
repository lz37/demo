<template>
  <div class="signup-container">
    <el-form :model="form" label-width="120px">
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
      <el-form-item label=" 确 认 密 码 ">
        <el-input
          v-model="form.confirmPassword"
          type="password"
          show-password
          clearable
        />
      </el-form-item>
      <el-form-item label=" 学 校 ">
        <el-select
          v-model="form.school"
          placeholder="请选择你的学校"
          size="large"
        >
          <el-option
            v-for="item in schools"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label=" 年 级 ">
        <el-input v-model="form.grade" clearable />
      </el-form-item>
      <el-form-item label=" 性 别 ">
        <el-radio-group v-model="form.sex">
          <el-radio label="男" size="large">男</el-radio>
          <el-radio label="女" size="large">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label=" 学 号 ">
        <el-input v-model="form.id" clearable />
      </el-form-item>
      <el-form-item>
        <el-button color="#1d1626" dark="isDark">确定</el-button>
        <el-button @click="this.$router.push('/')">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "SignupView",
  data() {
    return {
      form: {
        name: "",
        password: "",
        confirmPassword: "",
        school: "",
        grade: "",
        sex: "",
        id: "",
      },
      schools: [
        {
          value: "牛马小学",
          label: "牛马小学",
        },
        {
          value: "逆天小学",
          label: "逆天小学",
        },
      ],
    };
  },
};
</script>

<style>
.signup-container {
  width: 100%;
  height: 70%;
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
}
</style>
