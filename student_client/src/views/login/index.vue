<template>
  <div>
    <el-container>
      <el-header >
        <div style="text-align: center; font-size: 25px; font-weight: bolder">
          <i class="el-icon-s-home" style="margin-right: 25px"></i>
          选课管理系统
        </div>
      </el-header>
      <el-main>
        <el-card class="login-module">
          <div slot="header" class="clearfix">
            <span style="text-align: center; font-size: 20px; font-family: 'Microsoft YaHei'">
              <p><i class="el-icon-office-building" style="margin-right: 18px"></i>登陆</p>
            </span>
          </div>
          <div>
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
              <el-form-item label="用户 id" prop="id">
                <el-input v-model.number="ruleForm.id" prefix-icon="el-icon-lollipop"></el-input>
              </el-form-item>
              <el-form-item label="用户密码" prop="password">
                <el-input v-model="ruleForm.password" placeholder="请输入密码" show-password prefix-icon="el-icon-ice-cream-round"></el-input>
              </el-form-item>
              <el-form-item label="用户类型" prop="type">
                <el-radio-group v-model="ruleForm.type">
                  <el-radio label="student" value="student">学生</el-radio>
                  <el-radio label="teacher" value="teacher">老师</el-radio>
                  <el-radio label="admin" value="admin">admin</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">登陆</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
                <el-button @click="test('ruleForm')">test</el-button>
              </el-form-item>
            </el-form>
          </div>
        </el-card>
      </el-main>
    </el-container>
  </div>
</template>
<script>
export default {
  data() {
    return {
      ruleForm: {
        id: null,
        password: null,
        type: null,
      },
      rules: {
        id: [
          { required: true, message: '请输入用户 id', trigger: 'blur' },
          { type: 'number', message: '请输入数字', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        type: [
          { required: true, message: '请选择', trigger: 'change' }
        ],
      }
    };
  },
  methods: {
    submitForm(formName) {
      const that = this
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let check = false
          let name = null

          axios.get('http://localhost:10086/info/getCurrentTerm').then(function (resp) {
            sessionStorage.setItem("currentTerm", resp.data)
          })

          axios.get('http://localhost:10086/info/getForbidCourseSelection').then(function (resp) {
            sessionStorage.setItem("ForbidCourseSelection", resp.data)
          })

          if (that.ruleForm.type === 'admin' || that.ruleForm.type === 'teacher') {
            let form = {tid: that.ruleForm.id, password: that.ruleForm.password}
            console.log(form)
            axios.post("http://localhost:10086/teacher/login", form).then(function (resp) {
              console.log("教师登陆验证信息：" + resp.data)
              check = resp.data
              if (check === true) {
                axios.get("http://localhost:10086/teacher/findById/" + that.ruleForm.id).then(function (resp) {
                  console.log("登陆页正在获取用户信息" + resp.data)
                  name = resp.data.tname

                  sessionStorage.setItem("token", 'true')
                  sessionStorage.setItem("type", that.ruleForm.type)
                  sessionStorage.setItem("name", name)
                  sessionStorage.setItem("tid", resp.data.tid)

                  console.log('name: ' + name + ' ' + that.ruleForm.type + ' ' + resp.data.tid)

                  if (that.ruleForm.type === 'admin' && name === 'admin') {
                    that.$message({
                      showClose: true,
                      message: '登陆成功，欢迎 ' + name + '!',
                      type: 'success'
                    });
                    that.$router.push('/admin')
                  }
                  else if(that.ruleForm.type === 'teacher' && name !== 'admin') {
                    that.$message({
                      showClose: true,
                      message: '登陆成功，欢迎 ' + name + '!',
                      type: 'success'
                    });
                    that.$router.push('/teacher')
                  }
                  else {
                    that.$message({
                      showClose: true,
                      message: 'admin 登陆失败，检查登陆类型',
                      type: 'error'
                    });
                  }
                })
              }
              else {
                that.$message({
                  showClose: true,
                  message: '登陆失败，检查账号密码',
                  type: 'error'
                });
              }
            })
          }
          else if (that.ruleForm.type === 'student') {
            let form = {sid: that.ruleForm.id, password: that.ruleForm.password}
            axios.post("http://localhost:10086/student/login", form).then(function (resp) {
              console.log("学生登陆验证信息：" + resp.data)
              check = resp.data
              if (check === true) {
                axios.get("http://localhost:10086/student/findById/" + that.ruleForm.id).then(function (resp) {
                  console.log("登陆页正在获取用户信息" + resp.data)
                  name = resp.data.sname

                  sessionStorage.setItem("token", 'true')
                  sessionStorage.setItem("type", that.ruleForm.type)
                  sessionStorage.setItem("name", name)
                  sessionStorage.setItem("sid", resp.data.sid)

                  that.$message({
                    showClose: true,
                    message: '登陆成功，欢迎 ' + name + '!',
                    type: 'success'
                  });

                  console.log('正在跳转：' + '/' + that.ruleForm.type)

                  // 3. 路由跳转
                  that.$router.push({
                    path: '/' + that.ruleForm.type,
                    query: {}
                  })
                })
              }
              else {
                that.$message({
                  showClose: true,
                  message: '账号密码错误，请联系管理员',
                  type: 'error'
                });
              }
            })
          }
          else {
            console.log("! error type")
          }
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    test(forName) {
      console.log(this.ruleForm)
    }
  }
}
</script>

<style>
.login-module {
  /*width: 380px;*/
  /*height: 325px;*/
  margin-top: 60px;
  /*border: none;*/
  position: absolute;
  right: 500px;
  text-align: center;
  width: 30%;
}
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}
</style>