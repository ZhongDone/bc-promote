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
                                <el-button type="success"
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
                                         prop="createdDate"
                                         align="center">
                        </el-table-column>
                        <el-table-column fixed="right"
                                         label="操作"
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
                        roleCode:'',
                        roleName:'',
                    },
                    pageNo: 1,
                    pageSize: 10
                },
                tableData:[],
                loading:false,
                total:0,
            }
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

            },
            // 新增
            handleAdd(){

            },
            // 修改
            handleEdit(){

            },
            // 授权
            handleAuthorize(){

            },
            // 删除
            handleDelete(){

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
            }
        }
    }
</script>

<style scoped>
    #roleManage{
        height: 100%;
    }
</style>