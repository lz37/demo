<template>
  <el-button-group>
    <el-button type="primary">新增学校</el-button>
    <el-button type="danger">删除学校</el-button>
  </el-button-group>
  <el-table :data="schools" style="width: 100%">
    <el-table-column prop="name" label="学校名称" />
    <el-table-column prop="type" label="学校类型" />
    <el-table-column prop="address" label="学校地址" />
    <el-table-column prop="linkman" label="联系人姓名" />
    <el-table-column prop="phone" label="联系电话" />
  </el-table>
</template>

<script>
export default {
  name: "SchoolComponent",
  data() {
    return {
      schools: [
        {
          name: "牛马小学",
          type: "小学",
          address: "湖北省武汉市江夏区江夏大道",
          linkman: "三上悠亚",
          phone: "18888888888",
        },
        {
          name: "牛马中学",
          type: "初中",
          address: "湖北省武汉市江夏区江夏大道",
          linkman: "相泽南",
          phone: "17777777777",
        },
        {
          name: "逆天小学",
          type: "小学",
          address: "江苏省南京市玄武区玄武大道",
          linkman: "桥本有菜",
          phone: "19999999999",
        },
      ],
    };
  },
};
</script>

<style></style>
