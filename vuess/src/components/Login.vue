<!--
 * @功能描述: 
 * @作者: 郑文杰
 * @Date: 2019-10-09 15:24:04
 * @最新修改内容: 
 * @LastEditTime: 2020-03-15 13:04:48
 -->
<template>
  <div>
    <el-form
      :rules="rules"
      class="login-container"
      label-position="left"
      :model="loginForm"
      label-width="0px"
      v-loading="loading"
    >
      <h3 class="login_title">派车管理系统登录</h3>
      <el-form-item prop="username">
        <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="账号"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="密码"></el-input>
      </el-form-item>
      <el-checkbox class="login_remember" v-model="checked" label-position="left">记住密码</el-checkbox>
      <el-form-item style="width: 100%">
        <el-button type="primary" style="width: 30%" @click="submitClick">登录</el-button>
        <el-button type="primary" style="width: 30%" @click="registered">注册</el-button>
      </el-form-item>
    </el-form>
    <div>
      <el-dialog
        title="注册用户"
        :visible.sync="dialogVisible"
        width="50%"
        :close-on-click-modal="false"
      >
        <el-form :model="regForm" ref="regForm" label-width="100px">
          <el-row>
            <el-col :span="12">
              <el-form-item label="姓名:" prop="name">
                <el-input
                  v-model="regForm.name"
                  size="mini"
                  style="width: 150px"
                  placeholder="请输入姓名"
                ></el-input>
              </el-form-item>
              <el-form-item label="住宅电话:" prop="telephone">
                <el-input
                  v-model="regForm.telephone"
                  size="mini"
                  style="width: 150px"
                  placeholder="请输入住宅电话"
                ></el-input>
              </el-form-item>
              <el-form-item label="用户名:" prop="username">
                <el-input
                  v-model="regForm.username"
                  size="mini"
                  style="width: 150px"
                  placeholder="请输入用户名"
                ></el-input>
              </el-form-item>
              <el-form-item label="是否启用:" prop="enabled">
                <el-switch
                  v-model="regForm.enabled"
                  active-value="1"
                  inactive-value="0"
                  active-color="#13ce66"
                  inactive-color="#aaaaaa"
                  active-text="启用"
                  inactive-text="禁用"
                ></el-switch>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="手机号码:" prop="phone">
                <el-input
                  v-model="regForm.phone"
                  size="mini"
                  style="width: 150px"
                  placeholder="请输入手机号码"
                ></el-input>
              </el-form-item>
              <el-form-item label="联系地址:" prop="address">
                <el-input
                  v-model="regForm.address"
                  size="mini"
                  style="width: 150px"
                  placeholder="请输入联系地址"
                ></el-input>
              </el-form-item>
              <el-form-item label="密码:" prop="password">
                <el-input
                  v-model="regForm.password"
                  size="mini"
                  style="width: 150px"
                  placeholder="请输入密码"
                ></el-input>
              </el-form-item>
            </el-col>
          </el-row>

          <el-form-item label="备注:" prop="remark">
            <el-input prefix-icon="el-icon-edit" v-model="regForm.remark" placeholder="请输入备注"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button size="mini" @click="dialogVisible=false">取 消</el-button>
          <el-button size="mini" type="primary" @click="handletReg('regForm')">注 册</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      rules: {
        username: [
          { required: true, message: "请输入用户名", trigger: "blur" }
        ],
        password: [{ required: true, message: "请输入密码", trigger: "blur" }]
      },
      checked: true,
      loginForm: {
        username: "admin",
        password: "123"
      },
      loading: false,
      dialogVisible: false,
      regForm: {
        enabled: "1"
      },
      labelPosition: "right"
    };
  },
  mounted() {},
  methods: {
    submitClick: function() {
      var _this = this;
      this.loading = true;
      this.postRequest("/login", {
        username: this.loginForm.username,
        password: this.loginForm.password
      }).then(resp => {
        _this.loading = false;
        if (resp && resp.status == 200) {
          var data = resp.data;
          _this.$store.commit("login", data.obj);
          var path = _this.$route.query.redirect;
          _this.$router.replace({
            path: path == "/" || path == undefined ? "/home" : path
          });
        }
      });
    },
    registered() {
      //注册
      this.dialogVisible = true;
    },
    handletReg() {
      //注册确定
      console.log(this.regForm);
      this.postRequest("/system/hr/regs", this.regForm).then(res => {
        if (res && res.status == 200) {
          this.dialogVisible = false;
        }
      });
    }
  }
};
</script>
<style>
.login-container {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 180px auto;
  width: 350px;
  padding: 35px 35px 15px 35px;
  background: #fff;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #cac6c6;
}
.login_title {
  margin: 0px auto 40px auto;
  text-align: center;
  color: #505458;
}
.login_remember {
  margin: 0px 0px 35px 0px;
  text-align: left;
}
</style>
