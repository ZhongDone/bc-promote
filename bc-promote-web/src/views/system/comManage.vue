<template>
    <div id="comManage">
        <el-card class="box-card" style="height: 100%; font-size: 15px">
            <div slot="header" class="clearfix">
                <span><i class="el-icon-arrow-right"></i> 系统管理</span>
                <el-divider direction="vertical"></el-divider>
                <span>机构管理</span>
            </div>
            <div class="text item">
                <el-row style="margin-top: 0.7rem">
                    <el-col :span="6">
                        <el-tag effect="dark">机构列表 <i class="el-icon-arrow-down"></i></el-tag>
                    </el-col>
                    <el-col :span="18" >
                        <el-col :span="6">
                            <el-button type="primary" size="small" icon="el-icon-plus" @click="handleAdd">新增机构</el-button>
                        </el-col>
                    </el-col>
                </el-row>

                <el-row style="margin-top: 0.7rem">
                    <el-col :span="6">
                        <!--左边树形结构-->
                        <div class="showDataDiv">
                            <el-tree default-expand-all :data="treeData" :props="defaultProps" @node-click="handleNodeClick" node-key="id"></el-tree>
                        </div>
                    </el-col>
                    <el-col :span="18">
                        <!--表格-->
                        <div class="showDataDiv2">
                            <el-table
                                    :data="tableData"
                                    stripe
                                    style="width: 100%">
                                <el-table-column
                                        type="index"
                                        label="序号"
                                        align="center"
                                        width="80">
                                </el-table-column>
                                <el-table-column prop="id"
                                                 label="主键"
                                                 align="center"
                                                 v-if="false">
                                </el-table-column>
                                <el-table-column
                                        prop="level"
                                        label="机构级别"
                                        width="120">
                                </el-table-column>
                                <el-table-column
                                        prop="manageCode"
                                        label="机构编码"
                                        width="240">
                                </el-table-column>
                                <el-table-column
                                        prop="manageName"
                                        label="机构名称"
                                        width="180">
                                </el-table-column>
                                <el-table-column label="操作" align="center" width="200">
                                    <template slot-scope="scope">
                                        <el-button
                                                size="mini"
                                                type="primary"
                                                icon="el-icon-edit"
                                                :disabled="scope.row.id==0"
                                                @click="handleEdit(scope.row)">编辑</el-button>
                                        <el-button
                                                size="mini"
                                                type="danger"
                                                icon="el-icon-delete"
                                                :disabled="scope.row.id==0"
                                                @click="handleDelete(scope.row)">删除</el-button>
                                    </template>
                                </el-table-column>
                            </el-table>
                        </div>
                    </el-col>
                </el-row>
            </div>
        </el-card>
        <!--弹窗-->
        <el-dialog
                :title="dialogTitle"
                :visible.sync="dialogVisible"
                width="30%">
            <!--表单-->
            <el-form label-width="120px"
                     size="small"
                     :rules="rules"
                     ref="eportalManage"
                     :model="eportalManage">
                <el-form-item label="父节点机构"
                              prop="parentName">
                    <el-input v-model="eportalManage.parentName"
                              disabled
                              style="width: 220px"></el-input>
                </el-form-item>
                <el-form-item label="机构级别"
                              prop="level">
                    <el-input v-model="eportalManage.level"
                              disabled
                              style="width: 220px"></el-input>
                </el-form-item>
                <el-form-item label="机构编码"
                              prop="manageCode">
                    <el-input v-model="eportalManage.manageCode"
                              placeholder="请输入机构编码"
                              style="width: 220px"></el-input>
                </el-form-item>
                <el-form-item label="机构名称"
                              prop="manageName">
                    <el-input v-model="eportalManage.manageName"
                              placeholder="请输入资源图标"
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
        name: "comManage",
        data() {
            return {
                tabIndex:null,
                treeData: [],
                tableData: [],
                defaultProps: {
                    children: 'children',
                    label: 'label'
                },
                checkManageData:{},
                eportalManage:{
                    id:null,
                    parentId:null,
                    parentName:'',
                    level:'',
                    manageCode:'',
                    manageName:''
                },
                rules: {
                    manageCode: [{required: true, message: '请输入资源名称', trigger: 'blur'}],
                    manageName: [{required: true, message: '请输入资源路径', trigger: 'blur'}]
                },
                dialogTitle:'新增机构',
                dialogVisible:false,

            };
        },
        created() {
            this.queryTreeManage();
        },
        methods: {
            handleNodeClick(data) {
                console.log(data);
                this.tableData = data.children;
                this.checkManageData = data;
            },
            // 查询菜单树形结构
            queryTreeManage(){
                postRequest("/eportal/eportal-manage/getManageTree").then(data=>{
                    if(data.content != null){
                        this.treeData = data.content;
                        this.tableData = this.treeData;
                        this.checkManageData = {};
                    }
                });
            },

            // 新增
            handleAdd(){
                this.resetDTO();
                console.log('checkManageData.id',this.checkManageData.id)
                if(this.checkManageData.id == null || this.checkManageData.id == undefined){
                    this.$message.error('请选择父节点机构！');
                    return;
                }
                this.eportalManage.parentId = this.checkManageData.id;
                this.eportalManage.parentName = this.checkManageData.manageName;
                this.eportalManage.level = (Number(this.checkManageData.level) + 1)+'';
                this.dialogTitle='新增机构';
                this.dialogVisible=true;
            },
            // 编辑
            handleEdit(row){
                console.log(this.checkManageData);
                this.eportalManage = {
                    id:row.id,
                    parentId:row.parentId,
                    parentName:this.checkManageData.label,
                    level:row.level,
                    manageCode:row.manageCode,
                    manageName:row.manageName
                };
                this.dialogTitle='编辑机构';
                this.dialogVisible=true;
            },
            // 删除
            handleDelete(row){
                this.$confirm('确认删除机构?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    postRequest("/eportal/eportal-manage/update", {id:row.id,isDelete:'1'}).then(data=>{
                        if(data.code=='0'){
                            this.$message.success('删除成功！');
                            this.cancelButton();
                            this.queryTreeManage();
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

            // 提交按钮
            onSubmit(){
                if(this.dialogTitle=='编辑机构'){
                    this.$refs.eportalManage.validate((valid) => {
                        if(valid){
                            postRequest("/eportal/eportal-manage/update", this.eportalManage).then(data=>{
                                if(data.code=='0'){
                                    this.$message.success(data.message);
                                    this.cancelButton();
                                    this.queryTreeManage();
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
                if(this.dialogTitle=='新增机构'){
                    this.$refs.eportalManage.validate((valid) => {
                        if(valid){
                            postRequest("/eportal/eportal-manage/save", this.eportalManage).then(data=>{
                                if(data.code=='0'){
                                    this.$message.success(data.message);
                                    this.cancelButton();
                                    this.queryTreeManage();
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

            /*取消按钮*/
            cancelButton () {
                // 隐藏对话框
                this.dialogVisible = false;
                // 重置请求参数
                this.resetDTO();
            },
            resetDTO(){
                this.eportalManage = {
                    id:null,
                    parentId:null,
                    parentName:'',
                    level:'',
                    manageCode:'',
                    manageName:''
                }
            },
        }
    }
</script>

<style >
    #comManage{
        height: 100%;
    }
    .showDataDiv{
        border-radius: 10px;
        width: 80%;
        height: 500px;
        padding: 15px 15px;
        background: #fff;
        border: 1px solid #eaeaea;
    }
    .showDataDiv2{
        border-radius: 10px;
        width: 96%;
        height: 500px;
        padding: 15px 15px;
        background: #fff;
        border: 1px solid #eaeaea;
    }
    .el-tree-node:focus>.el-tree-node__content {
        color: #409EFE;
    }
</style>