<template>
    <div id="menuManage">
        <el-card class="box-card" style="height: 100%; font-size: 15px">
            <div slot="header" class="clearfix">
                <span><i class="el-icon-arrow-right"></i> 系统管理</span>
                <el-divider direction="vertical"></el-divider>
                <span>菜单管理</span>
            </div>
            <div class="text item">
                <el-row style="margin-top: 0.7rem">
                    <el-col :span="6">
                        <el-tag effect="dark">菜单列表 <i class="el-icon-arrow-down"></i></el-tag>
                    </el-col>
                    <el-col :span="18" >
                        <el-col :span="6">
                            <el-button type="primary" size="small" icon="el-icon-plus" @click="handleAdd">新增菜单</el-button>
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
                                        prop="resourceName"
                                        label="资源名称"
                                        width="120">
                                </el-table-column>
                                <el-table-column
                                        prop="resourcePath"
                                        label="资源路径"
                                        width="240">
                                </el-table-column>
                                <el-table-column
                                        prop="resourceIcon"
                                        label="资源图标"
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
                     ref="eportalResource"
                     :model="eportalResource">
                <el-form-item label="父节点资源"
                              prop="parentName">
                    <el-input v-model="eportalResource.parentName"
                              disabled
                              style="width: 220px"></el-input>
                </el-form-item>
                <el-form-item label="资源名称"
                              prop="resourceName">
                    <el-input v-model="eportalResource.resourceName"
                              placeholder="请输入资源名称"
                              oninput="value=value.replace(/^[0-9]+$/,'')"
                              style="width: 220px"></el-input>
                </el-form-item>
                <el-form-item label="资源路径"
                              prop="resourcePath">
                    <el-input v-model="eportalResource.resourcePath"
                              placeholder="请输入资源路径"
                              style="width: 220px"></el-input>
                </el-form-item>
                <el-form-item label="资源图标"
                              prop="resourceIcon">
                    <el-input v-model="eportalResource.resourceIcon"
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
        name: "menuManage",
        data() {
            return {
                tabIndex:null,
                treeData: [],
                tableData: [],
                defaultProps: {
                    children: 'children',
                    label: 'label'
                },
                checkMenuData:{},
                eportalResource:{
                    id:null,
                    parentId:null,
                    parentName:'',
                    resourceName:'',
                    resourcePath:'',
                    resourceIcon:''
                },
                rules: {
                    resourceName: [{required: true, message: '请输入资源名称', trigger: 'blur'}],
                    resourcePath: [{required: true, message: '请输入资源路径', trigger: 'blur'}],
                    resourceIcon: [{required: true, message: '请输入资源图标', trigger: 'blur'}]
                },
                dialogTitle:'新增菜单',
                dialogVisible:false,

            };
        },
        created() {
            this.queryTreeMenu();
        },
        methods: {
            handleNodeClick(data) {
                console.log(data);
                this.tableData = data.children;
                this.checkMenuData = data;
            },
            // 查询菜单树形结构
            queryTreeMenu(){
                postRequest("/eportal/eportal-resource/getMenuTree").then(data=>{
                    if(data.content != null){
                        //this.treeData = data.content;
                        this.treeData = [
                            {
                                id:0,
                                label:'菜单',
                                resourceName:'菜单',
                                resourcePath:'/',
                                resourceIcon:'el-icon-s-platform',
                                children:data.content
                            }
                        ];
                        this.tableData = this.treeData;
                        this.checkMenuData = {};
                    }
                });
            },

            // 新增
            handleAdd(){
                this.resetDTO();
                console.log('checkMenuData.id',this.checkMenuData.id)
                if(this.checkMenuData.id == null || this.checkMenuData.id == undefined){
                    this.$message.error('请先选择资源的父节点！');
                    return;
                }
                this.eportalResource.parentId = this.checkMenuData.id;
                this.eportalResource.parentName = this.checkMenuData.resourceName;
                this.dialogTitle='新增菜单';
                this.dialogVisible=true;
            },
            // 编辑
            handleEdit(row){
                console.log(this.checkMenuData);
                this.eportalResource = {
                    id:row.id,
                    parentId:row.parentId,
                    parentName:this.checkMenuData.label,
                    resourceName:row.resourceName,
                    resourcePath:row.resourcePath,
                    resourceIcon:row.resourceIcon
                };
                this.dialogTitle='编辑菜单';
                this.dialogVisible=true;
            },
            // 删除
            handleDelete(row){
                this.$confirm('确认删除菜单?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    postRequest("/eportal/eportal-resource/update", {id:row.id,isDelete:'1'}).then(data=>{
                        if(data.code=='0'){
                            this.$message.success('删除成功！');
                            this.cancelButton();
                            this.queryTreeMenu();
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
                if(this.dialogTitle=='编辑菜单'){
                    this.$refs.eportalResource.validate((valid) => {
                        if(valid){
                            postRequest("/eportal/eportal-resource/update", this.eportalResource).then(data=>{
                                if(data.code=='0'){
                                    this.$message.success(data.message);
                                    this.cancelButton();
                                    this.queryTreeMenu();
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
                    this.$refs.eportalResource.validate((valid) => {
                        if(valid){
                            postRequest("/eportal/eportal-resource/save", this.eportalResource).then(data=>{
                                if(data.code=='0'){
                                    this.$message.success(data.message);
                                    this.cancelButton();
                                    this.queryTreeMenu();
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
                this.eportalResource = {
                    id:null,
                    parentId:null,
                    parentName:'',
                    resourceName:'',
                    resourcePath:'',
                    resourceIcon:''
                }
            },
        }
    }
</script>

<style >
    #menuManage{
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