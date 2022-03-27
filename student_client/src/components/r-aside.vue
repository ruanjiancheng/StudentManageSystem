<template>
  <div>
    <el-aside width="200px" style="background-color: rgb(238, 241, 246); height: 810px; border: 1px solid #eee">
      <el-menu router :default-active="this.$route.path">
        <div v-for="(item, index) in $router.options.routes" v-if="item.name === type" :index="index+''" :key="index">
          <el-submenu v-for="(item2, index2) in item.children" :index="item2.path" :key="item2.path">
            <template slot="title"><i class="el-icon-menu"></i>{{ item2.name }}</template>
            <el-menu-item v-for="(item3, index3) in item2.children" :index="item3.path" :key="item3.path"><i class="el-icon-s-promotion"></i>{{ item3.name }}</el-menu-item>
          </el-submenu>
        </div>
      </el-menu>
    </el-aside>
  </div>
</template>

<script>
export default {
  name: "r-aside",
  data() {
    return {
      type: null
    }
  },
  created() {
    this.type = sessionStorage.getItem("type")
    console.log('aside: ' + this.type)
    console.log(this.$router.options)
  }
}
</script>

<style scoped>

</style>