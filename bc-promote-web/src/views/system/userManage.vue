<template>
    <div id="roleManage">
        <el-card class="box-card" style="height: 100%; font-size: 15px">
            <div slot="header" class="clearfix">
                <span><i class="el-icon-arrow-right"></i> 系统管理</span>
                <el-divider direction="vertical"></el-divider>
                <span>用户管理</span>
            </div>
            <div class="text item">
                <!--查询条件-->
                <div>
                    <el-form label-width="80px"
                             size="small"
                             label-position="right"
                             :model="queryDTO">
                        <el-row :gutter="20">
                            <el-col :span="6">
                                <el-form-item label="管理机构">
                                    <el-select size="small"
                                               v-model="queryDTO.entity.manageCode"
                                               placeholder="请选择管理机构"
                                               style="width: 302px">
                                        <el-option
                                                v-for="item in manageList"
                                                :key="item.manageCode"
                                                :label="item.manageName"
                                                :value="item.manageCode" />
                                    </el-select>
                                </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="用户名称">
                                    <el-input v-model="queryDTO.entity.accountName"
                                              placeholder="请输入用户名称"
                                              style="width: 302px"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-button type="primary"
                                           size="small"
                                           icon="el-icon-search"
                                           @click="queryPageList">查询</el-button>
                                <el-button type="info"
                                           size="small"
                                           icon="el-icon-refresh"
                                           @click="searchReset">重置</el-button>
                                <el-button type="primary"
                                           size="small"
                                           icon="el-icon-plus"
                                           @click="handleAdd()">新增</el-button>
                            </el-col>
                        </el-row>

                    </el-form>
                </div>
                <!-- 分割线 -->
                <div style="text-align: left">
                    <el-divider></el-divider>
                </div>
                <!--表格-->
                <div>
                    <el-table :data="tableData"
                              v-loading="loading"
                              size="small"
                              style="width: 100%;">
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
                        <el-table-column fixed="right"
                                         label="操作"
                                         width="300"
                                         align="center">
                            <template slot-scope="scope">
                                <el-button
                                        size="mini"
                                        type="primary"
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
                    <!--分页-->
                    <el-pagination @size-change="handleSizeChange"
                                   @current-change="handleCurrentChange"
                                   :current-page="queryDTO.pageNo"
                                   :page-sizes="[10, 20, 30, 40]"
                                   :page-size="queryDTO.pageSize"
                                   layout="total, sizes, prev, pager, next, jumper"
                                   :total="total"
                                   >
                    </el-pagination>
                </div>
                <!--新增/修改弹窗-->
                <el-dialog
                        :title="dialogTitle"
                        :visible.sync="dialogVisible"
                        width="30%">
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
                        <el-form-item label="用户角色"
                                      prop="roleCode">
                            <el-select v-model="eportalUser.roleCode" placeholder="请选择用户角色">
                                <el-option
                                        v-for="item in roleList"
                                        :key="item.roleCode"
                                        :label="item.roleName"
                                        :value="item.roleCode" />
                            </el-select>
                        </el-form-item>
                        <el-form-item label="管理机构" prop="manageCode">
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
        </el-card>
    </div>
</template>

<script>
    import {postRequest} from "../../utils/api";

    export default {
        name: "roleManage",
        data(){
            return{
                queryDTO:{
                    entity:{
                        manageCode: '',
                        accountName: '',
                        isDelete: '0'
                    },
                    pageNo: 1,
                    pageSize: 10
                },
                tableData:[],
                loading:false,
                total:0,
                dialogTitle:'新增用户',
                dialogVisible:false,
                resouceDialogVisible:false,

                eportalUser: {
                    id: null,
                    userName: "",
                    accountName: "",
                    roleName: "",
                    roleCode: "",
                    roleId: null,
                    manageCode: "",
                    manageName: "",
                    email: "",
                    mobile: ""
                },
                defaultProps: {
                    children: 'children',
                    label: 'label'
                },
                rules: {
                    userName: [{required: true, message: '请输入账号', trigger: 'blur'}],
                    accountName: [{required: true, message: '请输入账户名', trigger: 'blur'}],
                    roleCode: [{required: true, message: '请选择用户角色', trigger: 'blur'}],
                    email: [{required: true, message: '请输入邮箱', trigger: 'blur'}],
                    mobile: [{required: true, message: '请输入电话', trigger: 'blur'}],
                    manageCode: [{required: true, message: '请选择管理机构', trigger: 'blur'}]
                },
                treeData:[],
                authorizeRoleId:null,
                manageList: [],
                roleList: []

            }
        },
        created() {
            this.queryPageList();
            this.queryManageList();
            this.queryRoleList();
        },
        methods: {
            // 查询
            queryPageList(){
                postRequest("/eportal/eportal-user/queryListPage",this.queryDTO).then(data=>{
                    if(data.content != null){
                        this.tableData=data.content.records;
                        this.total = data.content.total;
                    }
                });
            },

            // 查询机构列表
            queryManageList(){
                postRequest("/eportal/eportal-manage/queryList", {parentId: 1, manageCode: ""}).then(data=>{
                    if(data.content != null){
                        this.manageList = data.content;
                    }
                });
            },

            // 查询机构列表
            queryRoleList(){
                postRequest("/eportal/eportal-role/queryList").then(data=>{
                    if(data.content != null){
                        this.roleList = data.content;
                    }
                });
            },

            // 重置查询条件
            searchReset(){
                this.queryDTO = {
                    entity:{
                        roleCode:'',
                        roleName:'',
                    },
                    pageNo: 1,
                    pageSize: 10
                }
            },
            // 新增
            handleAdd(){
                this.resetDTO();
                this.dialogTitle='新增用户';
                this.dialogVisible=true;
            },
            // 修改
            handleEdit(row){
                this.eportalUser = {
                    id: row.id,
                    userName: row.userName,
                    accountName: row.accountName,
                    roleName: row.roleName,
                    manageCode: row.manageCode,
                    manageName: row.manageName,
                    email: row.email,
                    mobile: row.mobile,
                    roleCode: row.roleCode,
                    roleId: row.roleId
                };
                this.dialogTitle='编辑用户';
                this.dialogVisible=true;
            },
            // 删除
            handleDelete(row){
                this.$confirm('确认删除用户?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    postRequest("/eportal/eportal-user/update", {id:row.id,isDelete:'1'}).then(data=>{
                        if(data.code=='0'){
                            this.$message.success('删除成功！');
                            this.cancelButton();
                            this.queryPageList();
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
            // 分页-数量修改
            handleSizeChange(val){
                this.queryDTO.pageSize = val;
                this.queryPageList();
            },
            // 分页-当前页修改
            handleCurrentChange(val){
                this.queryDTO.pageNo = val;
                this.queryPageList();
            },

            // 提交按钮
            onSubmit(){
                if(this.dialogTitle=='编辑用户'){
                    this.$refs.eportalUser.validate((valid) => {
                        if(valid){
                            let managers = this.manageList.filter(item =>{
                                return  item.manageCode == this.eportalUser.manageCode
                            })
                            this.eportalUser.manageName = managers[0].manageName;

                            let roles = this.roleList.filter(item =>{
                                return  item.roleCode == this.eportalUser.roleCode
                            })
                            this.eportalUser.roleName = roles[0].roleName;
                            this.eportalUser.roleId = roles[0].id;

                            postRequest("/eportal/eportal-user/update", this.eportalUser).then(data=>{
                                if(data.code=='0'){
                                    this.$message.success(data.message);
                                    this.cancelButton();
                                    this.queryPageList();
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
                if(this.dialogTitle=='新增用户'){
                    this.$refs.eportalUser.validate((valid) => {
                        if(valid){
                            let managers = this.manageList.filter(item =>{
                                return  item.manageCode == this.eportalUser.manageCode
                            })
                            this.eportalUser.manageName = managers[0].manageName;

                            let roles = this.roleList.filter(item =>{
                                return  item.roleCode == this.eportalUser.roleCode
                            })
                            this.eportalUser.roleName = roles[0].roleName;
                            this.eportalUser.roleId = roles[0].id;

                            postRequest("/eportal/eportal-user/save", this.eportalUser).then(data=>{
                                if(data.code=='0'){
                                    this.$message.success(data.message);
                                    this.cancelButton();
                                    this.queryPageList();
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
            //取消按钮
            cancelButton () {
                // 隐藏对话框
                this.dialogVisible = false;
                this.resouceDialogVisible = false;
                // 重置请求参数
                this.resetDTO();
            },

            // 重置参数
            resetDTO(){
                this.eportalUser = {
                    id: null,
                    userName: "",
                    accountName: "",
                    roleName: "",
                    manageCode: "",
                    manageName: "",
                    email: "",
                    mobile: "",
                    roleCode: "",
                    roleId: null,
                }
            },
        }
    }
</script>

<style scoped>
    #roleManage{
        height: 100%;
    }
</style>