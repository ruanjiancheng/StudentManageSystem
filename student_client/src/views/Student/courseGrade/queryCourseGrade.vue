<template>
  <div>
    <el-form >
      <el-form-item label="选择学期">
        <el-select v-model="term" placeholder="请选择学期">
          <el-option v-for="(item, index) in termList" :key="index" :label="item" :value="item"></el-option>
        </el-select>
      </el-form-item>
    </el-form>
    <el-card>
      <el-table
          :data="tableData"
          border
          style="width: 100%">
        <el-table-column
            fixed
            prop="cid"
            label="课号"
            width="150">
        </el-table-column>
        <el-table-column
            prop="cname"
            label="课程号"
            width="150">
        </el-table-column>
        <el-table-column
            prop="tid"
            label="教师号"
            width="150">
        </el-table-column>
        <el-table-column
            prop="tname"
            label="教师名称"
            width="150">
        </el-table-column>
        <el-table-column
            prop="ccredit"
            label="学分"
            width="150">
        </el-table-column>
        <el-table-column
            prop="grade"
            label="成绩"
            width="150">
        </el-table-column>
      </el-table>
      <p>
        平均成绩：{{ avg }}
      </p>
      <el-pagination
          background
          layout="prev, pager, next"
          :total="total"
          :page-size="pageSize"
          @current-change="changePage"
      >
      </el-pagination>
    </el-card>
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
      avg: 0,
      term: sessionStorage.getItem('currentTerm'),
      termList: null
    }
  },
  created() {
    const that = this
    axios.get('http://localhost:10086/SCT/findAllTerm').then(function (resp) {
      that.termList = resp.data
    })
  },
  watch: {
    term: {
      handler(newTerm, oldTerm) {
        const sid = sessionStorage.getItem('sid')
        const that = this
        axios.get('http://localhost:10086/SCT/findBySid/' + sid + '/' + newTerm).then(function (resp) {
          that.tmpList = resp.data
          that.total = resp.data.length
          let start = 0, end = that.pageSize
          let length = that.tmpList.length
          let ans = (end < length) ? end : length
          that.tableData = that.tmpList.slice(start, end)
          let totalScore = 0
          for (let i = 0; i < that.total; i++) {
            totalScore += that.tmpList[i].ccredit
            that.avg += that.tmpList[i].ccredit * that.tmpList[i].grade
          }
          if (totalScore === 0)
            that.avg = 0
          else
            that.avg /= totalScore
        })
      },
      immediate: true
    }
  }
}
</script>

<!--
  TODO：
  1. 管理员：
    1. 学生选课管理
    2. 成绩管理（只能当前学期）
  2. 学生：成绩排名
  3. 教师：成绩管理（？使用弹框）（只能当前学期）

-->