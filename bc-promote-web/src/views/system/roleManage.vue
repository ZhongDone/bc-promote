<template>
    <div id="roleManage">
        <el-card class="box-card" style="height: 100%; font-size: 15px">
            <div slot="header" class="clearfix">
                <span><i class="el-icon-arrow-right"></i> 系统管理</span>
                <el-divider direction="vertical"></el-divider>
                <span>角色管理</span>
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
                                <el-form-item label="角色编码">
                                    <el-input v-model="queryDTO.entity.roleCode"
                                              placeholder="请输入角色编码"></el-input>
                                </el-form-item>
                            </el-col>
                            <el-col :span="6">
                                <el-form-item label="角色名称">
                                    <el-input v-model="queryDTO.entity.roleName"
                                              placeholder="请输入角色名称"></el-input>
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
                        <el-table-column type="index"
                                         label="序号"
                                         width="80"
                                         align="center">
                        </el-table-column>
                        <el-table-column prop="id"
                                         label="主键"
                                         align="center"
                                         v-if="false">
                        </el-table-column>
                        <el-table-column prop="roleCode"
                                         label="角色编码"
                                         align="center">
                        </el-table-column>
                        <el-table-column prop="roleName"
                                         label="角色名称"
                                         align="center">
                        </el-table-column>
                        <el-table-column label="操作人"
                                         prop="createdUser"
                                         align="center">
                        </el-table-column>
                        <el-table-column label="操作时间"
                                         prop="modifiedDate"
                                         align="center">
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
                                        type="success"
                                        icon="el-icon-check"
                                        @click="handleAuthorize(scope.row)">授权</el-button>
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
                             ref="eportalRole"
                             :model="eportalRole">
                        <el-form-item label="角色编码"
                                      prop="roleCode">
                            <el-input v-model="eportalRole.roleCode"
                                      placeholder="请输入角色编码"
                                      style="width: 220px"></el-input>
                        </el-form-item>
                        <el-form-item label="角色名称"
                                      prop="roleName">
                            <el-input v-model="eportalRole.roleName"
                                      placeholder="请输入角色名称"
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
                <!--菜单资源弹窗-->
                <el-dialog
                        :title="dialogTitle"
                        :visible.sync="resouceDialogVisible"
                        width="30%">
                    <el-tree
                            :data="treeData"
                            show-checkbox
                            default-expand-all
                            node-key="id"
                            ref="tree"
                            highlight-current
                            :props="defaultProps">
                    </el-tree>
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
    import {deleteRequest, getRequest, postRequest} from "../../utils/api";

    export default {
        name: "roleManage",
        data(){
            return{
                queryDTO:{
                    entity:{
                        roleCode:'',
                        roleName:'',
                        isDelete: '0'
                    },
                    pageNo: 1,
                    pageSize: 10
                },
                tableData:[],
                loading:false,
                total:0,
                dialogTitle:'新增角色',
                dialogVisible:false,
                resouceDialogVisible:false,

                eportalRole:{
                    id:null,
                    roleCode:null,
                    roleName:null,
                },
                defaultProps: {
                    children: 'children',
                    label: 'label'
                },
                rules: {
                    roleName: [{required: true, message: '请输入角色名称', trigger: 'blur'}],
                    roleCode: [{required: true, message: '请输入角色编码', trigger: 'blur'}]
                },
                treeData:[],
                authorizeRoleId:null,

            }
        },
        created() {
            this.queryPageList();
        },
        methods: {
            // 查询
            queryPageList(){
                postRequest("/eportal/eportal-role/queryListPage",this.queryDTO).then(data=>{
                    if(data.content != null){
                        this.tableData=data.content.records;
                        this.total = data.content.total;
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
                this.dialogTitle='新增角色';
                this.dialogVisible=true;
            },
            // 修改
            handleEdit(row){
                this.eportalRole = {
                    id:row.id,
                    roleCode:row.roleCode,
                    roleName:row.roleName
                };
                this.dialogTitle='编辑角色';
                this.dialogVisible=true;
            },
            // 授权
            async handleAuthorize(row){
                this.authorizeRoleId=row.id;
                // 根据当前登录用户查询菜单
                await postRequest("/eportal/eportal-resource/getMenuTree").then(data=>{
                    if(data.content != null){
                        this.treeData = data.content;
                    }
                });
                this.dialogTitle='菜单资源';
                this.resouceDialogVisible = true;
                // 查询当前选择的角色已选中的菜单
                await getRequest("/eportal/eportal-resource/getMenuTree/"+row.id).then(data=>{
                    if(data.content != null){
                        let checkArr = this.getCheckResourceId(data.content);
                        this.setCheckedKeys(checkArr);
                    }
                });
                this.getCheckedKeys();
            },
            // 删除
            handleDelete(row){
                this.$confirm('确认删除角色?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    postRequest("/eportal/eportal-role/update", {id:row.id,isDelete:'1'}).then(data=>{
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
                if(this.dialogTitle=='编辑角色'){
                    this.$refs.eportalRole.validate((valid) => {
                        if(valid){
                            postRequest("/eportal/eportal-role/update", this.eportalRole).then(data=>{
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
                if(this.dialogTitle=='新增角色'){
                    this.$refs.eportalRole.validate((valid) => {
                        if(valid){
                            postRequest("/eportal/eportal-role/save", this.eportalRole).then(data=>{
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
                if(this.dialogTitle=='菜单资源'){
                    let checkArr = this.getCheckedKeys();
                    deleteRequest("/eportal/eportal-role-resource/delete/"+this.authorizeRoleId);
                    checkArr.forEach(item=>{
                        let dto = {
                            roleId:this.authorizeRoleId,
                            resourceId:item
                        }
                        postRequest("/eportal/eportal-role-resource/save", dto);
                    });
                    this.$message.success("处理成功！");
                    this.cancelButton();
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
                this.eportalRole = {
                    id:null,
                    roleCode:null,
                    roleName:null,
                }
            },
            // 获取已选中的节点
            getCheckedKeys() {
                const xz = [...this.$refs.tree.getCheckedKeys(), ...this.$refs.tree.getHalfCheckedKeys()];
                return xz;
            },
            // 设置选中的节点
            setCheckedKeys(checkData) {
                this.$refs.tree.setCheckedKeys(checkData);
            },

            //
            getCheckResourceId(data){
                let arr = [];
                data.forEach(item=>{
                    if(item.children && item.children.length >0){
                        let childrenArr = this.getCheckResourceId(item.children);
                        arr = [...arr,...childrenArr];
                    }else{
                        arr.push(item.id);
                    }
                })
                return arr;
            }

        }
    }
</script>

<style scoped>
    #roleManage{
        height: 100%;
    }
</style>