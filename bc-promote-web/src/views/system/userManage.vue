<template>
    <div id="userManage">
        <el-card class="box-card" style="height: 100%; font-size: 15px">
            <div slot="header" class="clearfix">
                <span><i class="el-icon-arrow-right"></i> 系统管理</span>
                <el-divider direction="vertical"></el-divider>
                <span>用户管理</span>
            </div>
            <div class="text item">
                <!--查询条件-->
                <div>
                    <el-button type="success" size="small" @click="handleAdd">新增用户</el-button>
                    <!--跟据机构搜索-->
                    <el-select size="small"
                               v-model="queryDTO.entity.manageCode"
                               placeholder="请选择管理机构"
                               style="margin-left: 50px"
                               @change="queryUserList()">
                        <el-option
                                v-for="item in manageList"
                                :key="item.manageCode"
                                :label="item.manageName"
                                :value="item.manageCode" />
                    </el-select>
                </div>

                <!--表格-->
                <div>
                    <el-table
                            :data="userData"
                            style="width: 100%">
                        <el-table-column
                                type="index"
                                width="50">
                        </el-table-column>
                        <el-table-column
                                prop="id"
                                label="编号"
                                width="180"
                                v-if="false">
                        </el-table-column>
                        <el-table-column
                                prop="userName"
                                label="账号"
                                width="180">
                        </el-table-column>
                        <el-table-column
                                prop="accountName"
                                label="账户名">
                        </el-table-column>
                        <el-table-column
                                prop="roleName"
                                label="角色名称">
                        </el-table-column>
                        <el-table-column
                                prop="manageCode"
                                label="管理机构编码">
                        </el-table-column>
                        <el-table-column
                                prop="manageName"
                                label="管理机构名称">
                        </el-table-column>
                        <el-table-column
                                prop="status"
                                label="状态">
                        </el-table-column>
                        <el-table-column
                                prop="email"
                                label="邮箱">
                        </el-table-column>
                        <el-table-column
                                prop="mobile"
                                label="电话">
                        </el-table-column>
                        <el-table-column
                                prop="createdDate"
                                label="创建日期">
                        </el-table-column>
                        <el-table-column label="操作" align="center" width="300">
                            <template slot-scope="scope">
                                <el-button
                                        size="mini"
                                        icon="el-icon-edit"
                                        @click="handleEdit(scope.row)">编辑</el-button>
                                <el-button
                                        size="mini"
                                        type="danger"
                                        icon="el-icon-delete"
                                        @click="handleDelete(scope.row)">删除</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>

            </div>
        </el-card>

        <!--弹窗-->
        <el-dialog
                :title="dialogTitle"
                :visible.sync="dialogVisible"
                width="25%">
            <!--表单-->
            <el-form label-width="120px"
                     size="small"
                     :rules="rules"
                     ref="eportalUser"
                     :model="eportalUser">
                <el-form-item label="账号"
                              prop="userName">
                    <el-input v-model="eportalUser.userName"
                              placeholder="请输入账号"
                              style="width: 220px"></el-input>
                </el-form-item>
                <el-form-item label="账户名"
                              prop="accountName">
                    <el-input v-model="eportalUser.accountName"
                              placeholder="请输入账户名"
                              style="width: 220px"></el-input>
                </el-form-item>
                <el-form-item label="角色名称"
                              prop="roleName">
                    <el-input v-model="eportalUser.roleName"
                              placeholder="请输入角色名称"
                              style="width: 220px"></el-input>
                </el-form-item>
                <el-form-item label="管理机构">
                    <el-select v-model="eportalUser.manageCode" placeholder="请选择管理机构">
                        <el-option
                            v-for="item in manageList"
                            :key="item.manageCode"
                            :label="item.manageName"
                            :value="item.manageCode" />
                    </el-select>
                </el-form-item>

                <el-form-item label="邮箱"
                              prop="email">
                    <el-input v-model="eportalUser.email"
                              placeholder="请输入邮箱"
                              style="width: 220px"></el-input>
                </el-form-item>
                <el-form-item label="电话"
                              prop="mobile">
                    <el-input v-model="eportalUser.mobile"
                              placeholder="请输入电话"
                              style="width: 220px"></el-input>
                </el-form-item>
            </el-form>
            <!--底部确定取消-->
            <div slot="footer"
                 class="dialog-footer">
                <el-button @click="cancelButton()"
                           size="small">取 消</el-button>

                <el-button type="primary"
                           @click="onSubmit()"
                           size="small">保存</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import {postRequest} from "../../utils/api";

    export default {
        name: "userManage",
        created() {
            this.queryUserList();
            this.queryManageList()
        },
        data() {
            return {
                // 查询用户条件
                queryDTO: {
                    pageNo: 1,
                    pageSize: 10,
                    entity: {
                        isDelete: '0',
                        manageCode: ''
                    }
                },
                // 用户列表
                userData: [],
                // 模态框标题
                dialogTitle:'新增菜单',
                // 模态框状态
                dialogVisible:false,
                // 输入框校验
                rules: {
                    userName: [{required: true, message: '请输入账号', trigger: 'blur'}],
                    accountName: [{required: true, message: '请输入账户名', trigger: 'blur'}],
                    roleName: [{required: true, message: '请输入角色名称', trigger: 'blur'}],
                    email: [{required: true, message: '请输入邮箱', trigger: 'blur'}],
                    mobile: [{required: true, message: '请输入电话', trigger: 'blur'}]
                },
                // 模态框信息
                eportalUser: {
                    id: null,
                    userName: "",
                    accountName: "",
                    roleName: "",
                    manageCode: "",
                    manageName: "",
                    email: "",
                    mobile: ""
                },
                // 机构列表
                manageList: [],
                // 机构入参条件
                queryManageDTO: {
                    parentId: 1,
                    manageCode: "",
                }
            }
        },
        methods: {
            // 查询用户列表
            queryUserList() {
                postRequest("/eportal/eportal-user/queryListPage", this.queryDTO).then(data=>{
                    if(data.content != null) {
                        this.userData = data.content.records;
                    }
                })
            },

            // 查询机构列表
            queryManageList(){
                postRequest("/eportal/eportal-manage/queryList", this.queryManageDTO).then(data=>{
                    if(data.content != null){
                        this.manageList = data.content;
                    }
                });
            },

            // 新增按钮
            handleAdd(){
                this.resetDTO();
                this.userData.id = null;
                this.dialogTitle='新增菜单';
                this.dialogVisible=true;
            },


            // 编辑
            handleEdit(row){
                // 获取记录行信息
                this.eportalUser = {
                    id: row.id,
                    userName: row.userName,
                    accountName: row.accountName,
                    roleName: row.roleName,
                    manageCode: row.manageCode,
                    manageName: row.manageName,
                    email: row.email,
                    mobile: row.mobile
                };
                this.dialogTitle='编辑菜单';
                this.dialogVisible=true;
            },

            // 保存
            onSubmit() {
                if(this.dialogTitle=='编辑菜单'){
                    let name = this.manageList.filter(item =>{
                        console.log(item,'itme')

                        return  item.manageCode == this.eportalUser.manageCode
                    })
                    this.eportalUser.manageName = name[0].manageName;
                    // 修改方法
                    this.$refs.eportalUser.validate((valid) => {
                        if(valid){
                            postRequest("/eportal/eportal-user/update", this.eportalUser).then(data=>{
                                if(data.code=='0'){
                                    this.$message.success(data.message);
                                    // 关闭模态框
                                    this.cancelButton();
                                    // 刷新用户列表
                                    this.queryUserList();
                                }else{
                                    this.$message.error(data.message);
                                }
                            });
                        }else {
                            this.$message.error('请输入完整的信息！')
                            return false;
                        }
                    });
                }
                if(this.dialogTitle=='新增菜单'){
                    // 获取管理机构
                    let name = this.manageList.filter(item =>{
                        console.log(item,'itme')

                        return  item.manageCode == this.eportalUser.manageCode
                    })
                    this.eportalUser.manageName = name[0].manageName;
                    // 新增方法
                    this.$refs.eportalUser.validate((valid) => {
                        if(valid){
                            postRequest("/eportal/eportal-user/save", this.eportalUser).then(data=>{
                                if(data.code=='0'){
                                    this.$message.success(data.message);
                                    // 关闭模态框
                                    this.cancelButton();
                                    // 刷新用户列表
                                    this.queryUserList();
                                }else{
                                    this.$message.error(data.message);
                                }
                            });
                        }else {
                            this.$message.error('请输入完整的信息！')
                            return false;
                        }
                    });
                }
            },

            // 删除用户
            handleDelete(row) {
                this.$confirm('确认删除菜单?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    postRequest("/eportal/eportal-user/update", {id:row.id,isDelete:'1'}).then(data=>{
                        if(data.code=='0'){
                            this.$message.success('删除成功！');
                            this.cancelButton();
                            this.queryUserList();
                        }else{
                            this.$message.error(data.message);
                        }
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },

            // 取消按钮
            cancelButton () {
                // 隐藏对话框
                this.dialogVisible = false;
                // 重置请求参数
                this.resetDTO();
            },

            // 清空模态框用户数据
            resetDTO() {
                this.eportalUser =  {
                    id: null,
                    userName: "",
                    accountName: "",
                    roleName: "",
                    manageCode: "",
                    manageName: "",
                    email: "",
                    mobile: ""
                }
            }
        },
    }
</script>

<style scoped>
    #userManage{
        height: 100%;
    }
</style>