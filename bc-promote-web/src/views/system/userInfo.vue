<template>
    <div id="userinfo">
        <el-card class="box-card" style="height: 100%; font-size: 15px">
            <div slot="header" class="clearfix">
                <span><i class="el-icon-arrow-right"></i> 系统管理</span>
                <el-divider direction="vertical"></el-divider>
                <span>修改密码</span>
            </div>
            <div class="text item">
                <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="密码" prop="pass">
                        <el-input type="password" v-model="ruleForm.pass" autocomplete="off" style="width: 260px"></el-input>
                    </el-form-item>
                    <el-form-item label="确认密码" prop="checkPass">
                        <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off" style="width: 260px"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                        <el-button @click="resetForm('ruleForm')">重置</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </el-card>
    </div>
</template>

<script>
    import {getRequest, postRequest} from "../../utils/api";

    export default {
        name: "userInfo",
        data() {
            var validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else if (value !== '' && this.password === this.$md5(value)){
                    callback(new Error('旧密码与新密码一致！请重新输入'));
                }else {
                    if (this.ruleForm.checkPass !== '') {
                        this.$refs.ruleForm.validateField('checkPass');
                    }
                    callback();
                }
            };
            var validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.ruleForm.pass) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            return {
                ruleForm: {
                    pass: '',
                    checkPass: '',
                    age: ''
                },
                rules: {
                    pass: [
                        { validator: validatePass, trigger: 'blur' }
                    ],
                    checkPass: [
                        { validator: validatePass2, trigger: 'blur' }
                    ]
                },
                password:'',
                userId:null,
            };
        },
        created() {
            this.getUserInfo();
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        postRequest('/eportal/eportal-user/update', {id:this.userId,password:this.$md5(this.ruleForm.pass)}).then(data=>{
                            if(data.code=='0'){
                                this.$alert('密码修改成功，请重新登陆！', '提示', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        console.log(action)
                                        getRequest('/eportal/loginOut').then(data => {
                                            console.log(data);
                                            this.$router.replace('/');
                                        })
                                    }
                                });
                            }else{
                                this.$message.error(data.message);
                            }
                        });
                    } else {
                        this.$message.error("请输入正确信息");
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            getUserInfo(){
                getRequest('/eportal/getUserInfo').then(data => {
                    this.password = data.content.password;
                    this.userId = data.content.id;
                })
            },
        }
    }
</script>

<style scoped>

</style>