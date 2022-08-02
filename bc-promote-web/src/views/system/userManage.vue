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
                        <el-table-column align="right">
                            <template slot="header" slot-scope="scope">
                                <el-input
                                        v-model="search"
                                        size="mini"
                                        placeholder="输入关键字搜索"/>
                            </template>
                            <template slot-scope="scope">
                                <el-button
                                        size="mini"
                                        @click="handleEdit(scope.$index, scope.row)"  icon="el-icon-edit"></el-button>
                                <el-button
                                        size="mini"
                                        type="danger"
                                        @click="handleDelete(scope.$index, scope.row)" icon="el-icon-delete"></el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </div>

            </div>
        </el-card>
    </div>
</template>

<script>
    import {postRequest} from "../../utils/api";

    export default {
        name: "userManage",
        created() {
            this.queryUserList()
        },
        data() {
            return {
                // 查询用户条件
                eportalUser: {
                    pageNo: 1,
                    pageSize: 10,
                    entity: {
                    }
                },
                // 用户列表
                userData: [],

            }
        },
        methods: {
            handleDelete(index, row) {
                console.log(index, row);
            },
            queryUserList() {
                postRequest("/eportal/eportal-user/queryListPage", this.eportalUser).then(data=>{
                    if(data.content != null) {
                        this.userData = data.content.records;
                    }
                })
            },
            // 新增
            handleAdd(){
                this.userData.id = null;
                this.dialogTitle='新增菜单';
                this.dialogVisible=true;
            },
            // 编辑
            handleEdit(index, row){
                this.dialogTitle='编辑菜单';
                this.dialogVisible=true;


            },
        },
    }
</script>

<style scoped>
    #userManage{
        height: 100%;
    }
</style>