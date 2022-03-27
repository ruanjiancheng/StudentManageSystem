<template>
  <div>
    <el-container>
      <el-main>
        <el-card>
          <el-form :inline="true" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="120px" class="demo-ruleForm">
            <el-form-item label="课程号" prop="cid">
              <el-input v-model.number="ruleForm.cid"></el-input>
            </el-form-item>
            <el-form-item label="课程名" prop="cname">
              <el-input v-model="ruleForm.cname"></el-input>
            </el-form-item>
            <el-form-item label="模糊查询" prop="fuzzy">
              <el-switch v-model="ruleForm.fuzzy"></el-switch>
            </el-form-item>
            <el-form-item label="学分下限" prop="lowBound">
              <el-input v-model.number="ruleForm.lowBound"></el-input>
            </el-form-item>
            <el-form-item label="学分上限" prop="highBound">
              <el-input v-model.number="ruleForm.highBound"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
          </el-form>
        </el-card>
        <el-card style="margin-top: 10px">
          <offer-course-list :rule-form="ruleForm"></offer-course-list>
        </el-card>
      </el-main>
    </el-container>
  </div>
</template>
<script>
import OfferCourseList from "@/views/Teacher/offerCourseList";
export default {
  components: {OfferCourseList},
  data() {
    return {
      ruleForm: {
        cid: null,
        cname: null,
        fuzzy: true,
        lowBound: null,
        highBound: null
      },
      rules: {
        cid: [
          { type: 'number', message: '必须是数字类型' }
        ],
        cname: [
        ],
        lowBound: [
          { type: 'number', message: '必须是数字类型' }
        ],
        highBound: [
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