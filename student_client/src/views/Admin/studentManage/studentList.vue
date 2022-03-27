<template>
  <div>
    <el-table
        :data="tableData"
        border
        style="width: 100%">
      <el-table-column
          fixed
          prop="sid"
          label="学号"
          width="150">
      </el-table-column>
      <el-table-column
          prop="sname"
          label="姓名"
          width="120">
      </el-table-column>
      <el-table-column
          prop="password"
          label="密码"
          width="120">
      </el-table-column>
      <el-table-column
          label="操作"
          width="100">
        <template slot-scope="scope">
          <el-popconfirm
              confirm-button-text='删除'
              cancel-button-text='取消'
              icon="el-icon-info"
              icon-color="red"
              title="删除不可复原"
              @confirm="deleteStudent(scope.row)"
          >
            <el-button slot="reference" type="text" size="small">删除</el-button>
          </el-popconfirm>
          <el-button @click="editor(scope.row)" type="text" size="small">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        background
        layout="prev, pager, next"
        :total="total"
        :page-size="pageSize"
        @current-change="changePage"
    >
    </el-pagination>
  </div>
</template>

<script>
export default {
  methods: {
    deleteStudent(row) {
      const that = this
      axios.get('http://localhost:10086/student/deleteById/' + row.sid).then(function (resp) {
        if (resp.data === true) {
          that.$message({
            showClose: true,
            message: '删除成功',
            type: 'success'
          });
          console.log(that.tmpList === null)
          if (that.tmpList === null) {
            window.location.reload()
          }
          else {
            that.$router.push('/queryStudent')
          }
        }
        else {
          that.$message({
            showClose: true,
            message: '删除出错，请查询数据库连接',
            type: 'error'
          });
        }
      }).catch(function (e) {
        that.$message({
          showClose: true,
          message: '删除出错，存在外键依赖',
          type: 'error'
        });
      })
    },
    changePage(page) {
      page = page - 1
      if (this.tmpList === null) {
        const that = this
        axios.get('http://localhost:10086/student/findByPage/' + page + '/' + that.pageSize).then(function (resp) {
          that.tableData = resp.data
        })
      }
      else {
        let that = this
        let start = page * that.pageSize, end = that.pageSize * (page + 1)
        let length = that.tmpList.length
        let ans = end < length ? end : length
        that.tableData = that.tmpList.slice(start, ans)
      }
    },
    editor(row) {
      this.$router.push({
        path: '/editorStudent',
        query: {
          sid: row.sid
        }
      })
    }
  },

  data() {
    return {
      tableData: null,
      pageSize: 7,
      total: null,
      ruleForm: null,
      tmpList: null
    }
  },

  created() {
    if (this.tmpList !== null)
      this.tmpList = null
    const that = this
    // 是否从查询页跳转
    this.ruleForm = this.$route.query.ruleForm
    if (this.$route.query.ruleForm === undefined || (this.ruleForm.sid === null && this.ruleForm.sname === null)) {
      axios.get('http://localhost:10086/student/getLength').then(function (resp) {
        console.log("获取列表总长度: " + resp.data)
        that.total = resp.data
      })

      axios.get('http://localhost:10086/student/findByPage/0/' + that.pageSize).then(function (resp) {
        that.tableData = resp.data
      })
    }
    else {
      // 从查询页跳转并且含查询
      console.log('正在查询跳转数据')
      console.log(this.ruleForm)
      axios.post('http://localhost:10086/student/findBySearch', this.ruleForm).then(function (resp) {
        console.log('获取查询数据：')
        that.tmpList = resp.data
        that.total = resp.data.length
        console.log(that.tmpList)
        let start = 0, end = that.pageSize
        let length = that.tmpList.length
        let ans = end < length ? end : length
        that.tableData = that.tmpList.slice(start, ans)
      })
    }
  }
}
</script>