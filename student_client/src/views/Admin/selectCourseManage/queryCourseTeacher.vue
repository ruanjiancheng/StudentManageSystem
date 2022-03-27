<template>
  <div>
    <el-container>
      <el-main>
        <el-card>
          <el-form :inline="true" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="150px" class="demo-ruleForm">
            <el-form-item label="工号" prop="tid">
              <el-input v-model.number="ruleForm.tid"></el-input>
            </el-form-item>
            <el-form-item label="教师名" prop="tname">
              <el-input v-model.number="ruleForm.tname"></el-input>
            </el-form-item>
            <el-form-item label="教师模糊查询">
              <el-switch v-model="ruleForm.tFuzzy"></el-switch>
            </el-form-item>
            <el-form-item label="课程号" prop="cid">
              <el-input v-model.number="ruleForm.cid"></el-input>
            </el-form-item>
            <el-form-item label="课程名" prop="cname">
              <el-input v-model.number="ruleForm.cname"></el-input>
            </el-form-item>
            <el-form-item label="课程模糊查询">
              <el-switch v-model="ruleForm.cFuzzy"></el-switch>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
          </el-form>
        </el-card>
        <el-card style="margin-top: 10px">
          <course-tacher-list :ruleForm="ruleForm"></course-tacher-list>
        </el-card>
      </el-main>
    </el-container>
  </div>
</template>
<script>
import CourseTacherList from "@/views/Admin/selectCourseManage/CourseTacherList";
export default {
  components: {CourseTacherList},
  data() {
    return {
      ruleForm: {
        tid: null,
        cid: null,
        cname: null,
        tname: null,
        tFuzzy: true,
        cFuzzy: true
      },
      rules: {
        tid: [
          { type: 'number', message: '必须是数字类型' }
        ],
        cid: [
          { type: 'number', message: '必须是数字类型' }
        ],
      }
    };
  },
  methods: {
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>