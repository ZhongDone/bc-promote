<template>
    <div>
        <el-row>
            <!--左边图片-->
            <el-col :span="12">
                <img alt="Vue logo" src="../assets/login_bg.png" style="margin: 20% 20%; width: 90%">
            </el-col>
            <!--右边登录框-->
            <el-col :span="12">
                <el-form v-loading="loading" element-loading-text="正在登录......" element-loading-spinner="el-icon-loading"
                         element-loading-background="rgba(0, 0, 0, 0.8)" ref="loginForm" :model="loginForm" :rules="rules"
                         class="loginContainer">
                    <h3 class="loginTitle">互联网运营管理平台</h3>
                    <!-- 自动补全 auto-complete="false" -->
                    <el-form-item prop="username">
                        <el-input type="text" auto-complete="false" v-model="loginForm.userName"
                                  placeholder="请输入用户名"></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input type="password" auto-complete="false" v-model="loginForm.password"
                                  placeholder="请输入密码"></el-input>
                    </el-form-item>
                    <el-form-item style="text-align: left">
                        <el-checkbox v-model="checked" class="loginRemember">记住我</el-checkbox>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" style="width: 100%" @click="submitLogin">登录</el-button>
                    </el-form-item>
                </el-form>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import {postRequest} from "../utils/api";
    export default {
        name: "Login",
        components: {},
        props: [],
        data() {
            return {
                loginForm: {
                    userName: '',
                    password: ''
                },
                loading: false, // 加载中
                checked: true,
                rules: {
                    userName: [{required: true, message: '请输入用户名', trigger: 'blur'}],
                    password: [{required: true, message: '请输入密码', trigger: 'blur'}]
                }
            }
        },
        methods: {
            submitLogin() {
                // 登录
                this.$refs.loginForm.validate((valid) => {
                    if (valid) {
                        //点击登录后开启的加载等待的css样式  this.loading = true;
                        this.loading = true;
                        //验证登录表单数据，得到该接口的返回值
                        postRequest('/eportal/login', this.loginForm).then(getReturnValue => {
                            this.loading = false;
                            if(getReturnValue.code=='0'){
                                const token = getReturnValue.content.token;
                                window.sessionStorage.setItem('token', token);
                                this.$router.replace('/Home');
                            }else{
                                this.$message.error(getReturnValue.message);
                            }
                        }).finally(()=>{
                            this.loading = false;
                        })
                    } else {
                        this.$message.error('请输入用户名密码！')
                        return false;
                    }
                })
            }
        }
    }
</script>

<style space>
    .loginContainer {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 30% auto;
        width: 350px;
        padding: 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }
    .loginTitle {
        text-align: center;
    }
</style>