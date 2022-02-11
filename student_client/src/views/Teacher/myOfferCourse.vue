<template>
  <div>
    <el-container>
      <el-main>
        <h1>当前学期开设的课程</h1>
        <el-card>
          <el-table
              :data="tableData"
              border
              stripe
              style="width: 100%">
            <el-table-column
                fixed
                prop="cid"
                label="课程号"
                width="150">
            </el-table-column>
            <el-table-column
                prop="cname"
                label="课程名"
                width="150">
            </el-table-column>
            <el-table-column
                prop="ccredit"
                label="学分"
                width="150">
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
        </el-card>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  methods: {
    changePage(page) {
      page = page - 1
      const that = this
      let start = page * that.pageSize, end = that.pageSize * (page + 1)
      let length = that.tmpList.length
      let ans = (end < length) ? end : length
      that.tableData = that.tmpList.slice(start, ans)
    },
  },

  data() {
    return {
      tableData: null,
      pageSize: 10,
      total: null,
      tmpList: null,
      tid: null,
      term: null
    }
  },
  created() {
    this.tid = sessionStorage.getItem("tid");
    this.term = sessionStorage.getItem("currentTerm");

    console.log(this.term)

    const that = this
    axios.get('http://localhost:10086/courseTeacher/findMyCourse/' + this.tid + '/' + this.term).then(function (resp) {

      that.tmpList = resp.data
      that.total = resp.data.length
      let start = 0, end = that.pageSize
      let length = that.tmpList.length
      let ans = (end < length) ? end : length
      that.tableData = that.tmpList.slice(start, ans)

    })

  }

}
</script>