<template>
  <div>
      <el-popconfirm
          confirm-button-text='好的'
          cancel-button-text='不用了'
          icon="el-icon-info"
          icon-color="red"
          title="确定退出登陆吗？"
          @confirm="out"
      >
        <el-button slot="reference" type="danger" style="margin-top: 10px" round @click>
          logout
        </el-button>
      </el-popconfirm>
  </div>
</template>

<script>
export default {
  name: "logout",
  methods: {
    out() {
      sessionStorage.removeItem("token")
      this.$router.push('/')
    }
  }
}
</script>

<style scoped>

</style>