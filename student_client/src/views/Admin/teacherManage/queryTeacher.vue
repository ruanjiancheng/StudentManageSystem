<template>
  <div>
    <el-container>
      <el-main>
        <el-card>
          <el-form :inline="true" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="工号" prop="tid">
              <el-input v-model.number="ruleForm.tid"></el-input>
            </el-form-item>
            <el-form-item label="教师姓名" prop="tname">
              <el-input v-model="ruleForm.tname"></el-input>
            </el-form-item>
            <el-form-item label="模糊查询" prop="fuzzy">
              <el-switch v-model="ruleForm.fuzzy"></el-switch>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
          </el-form>
        </el-card>
        <el-card>
          <teacher-list :ruleForm="this.ruleForm"></teacher-list>
        </el-card>
      </el-main>
    </el-container>
  </div>
</template>
<script>
import TeacherList from "@/views/Admin/teacherManage/teacherList";
export default {
  components: {TeacherList},
  data() {
    return {
      ruleForm: {
        tid: null,
        tname: null,
        fuzzy: true
      },
      rules: {
        tid: [
          { type: 'number', message: '必须是数字类型' }
        ],
        tname: [

        ],
      }
    };
  },
  create() {
    this.tid = null
    this.tname = null
    this.fuzzy = true
  },
  methods: {
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>