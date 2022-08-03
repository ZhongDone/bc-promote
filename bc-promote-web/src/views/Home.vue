<template>
    <div id="home" class="home">
        <el-container style="height: 100vh">
            <el-header class="homeHeader" style="height: 60px;">
                <!--图标系统名称-->
                <div style="width: 250px;height: 100%; background: #545c64; float:left">
                    <h4 style="color: #fff;text-align: center">互联网运营管理平台</h4>
                </div>
                <!--顶部路由地址-->
                <div style="float:left;">
                </div>
                <!--个人信息-->
                <div style="float:right;width:200px; line-height: 60px">
                    <el-avatar style="vertical-align:middle" src="https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80"></el-avatar>
                    <el-dropdown style="vertical-align:middle;margin-left: 15px">
                        <span class="el-dropdown-link">{{accountName}}<i class="el-icon-arrow-down el-icon--right"></i></span>
                        <el-dropdown-menu slot="dropdown">
                            <el-dropdown-item><span @click="modifyPassword()">修改密码</span></el-dropdown-item>
                            <el-dropdown-item><span @click="loginOut()">退出登录</span></el-dropdown-item>
                        </el-dropdown-menu>
                    </el-dropdown>
                </div>
            </el-header>
            <el-container class="homeContainer">
                <!--侧边栏菜单-->
                <el-aside width="250px" class="homeAside">
                    <el-menu
                            router
                            :default-active="currentMenu"
                            class="el-menu-vertical-demo"
                            background-color="#545c64"
                            text-color="#fff"
                            active-text-color="#409EFF"
                            size="small">
                        <div v-for="(item,index) in routes" :key="index">
                            <!-- 一级菜单（没有任何子级菜单）-->
                            <el-menu-item :index="item.path" v-if="!item.children"><i :class="item.iconCls"></i>{{item.name}}</el-menu-item>
                            <!-- 一级菜单（有子级菜单）-->
                            <el-submenu :index="item.path" v-else>
                                <template slot="title"><i :class="item.iconCls"></i>{{item.name}}</template>

                                <!-- 遍历二级菜单容器 -->
                                <div v-for="(item1,index) in item.children" :key="index">
                                    <!-- 判断二级菜单（没有三级菜单）-->
                                    <el-menu-item :index="item1.path" v-if="!item1.children"><i :class="item1.iconCls"></i>{{item1.name}}</el-menu-item>

                                    <!-- 判断二级菜单（有三级菜单）-->
                                    <el-submenu :index="item1.path" v-else>
                                        <template slot="title"><i :class="item1.iconCls"></i>{{item1.name}}</template>

                                        <!-- 遍历三级菜单容器 -->
                                        <div v-for="(item2,index) in item1.children" :key="index">
                                            <!-- 判断二级菜单（没有三级菜单）-->
                                            <el-menu-item :index="item2.path" v-if="!item2.children"><i :class="item2.iconCls"></i>{{item2.name}}</el-menu-item>

                                            <!-- 判断二级菜单（有三级菜单）-->
                                            <el-submenu :index="item2.path" v-if="item2.children">
                                                <template slot="title"><i :class="item2.iconCls"></i>{{item2.name}}</template>
                                                <el-menu-item :index="item3.path" v-for="(item3,index) in item2.children" :key="index"><i :class="item3.iconCls"></i>{{item3.name}}</el-menu-item>
                                            </el-submenu>
                                        </div>
                                    </el-submenu>
                                </div>
                            </el-submenu>
                        </div>
                    </el-menu>
                </el-aside>
                <!--主内容-->
                <el-main style="background: #f2f6fc">
                    <router-view/>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    import {getRequest} from "../utils/api";

    export default {
        name: 'Home',
        watch: {
            '$route' (to, from) {
                console.log(to);
                console.log(from);
                this.getCurrentMenu()
            }
        },
        data() {
            return {
                accountName:'',
                currentMenu:'/home',
            }
        },
        computed:{
            routes(){
                return this.$store.state.routes;
            }
        },
        created() {
            this.getUserInfo();
            this.getCurrentMenu();
        },
        methods: {
            getUserInfo(){
                getRequest('/eportal/getUserInfo').then(data => {
                    this.accountName = data.content.accountName;
                })
            },
            loginOut(){
                getRequest('/eportal/loginOut').then(data => {
                    console.log(data);
                    this.$router.replace('/');
                })
            },
            modifyPassword(){
                this.$router.push('/system/userInfo')
            },
            getCurrentMenu () {
                // 获取当前页面 菜单刷新后能自动选中
                let currentUrl = window.location.href;
                console.log("bbbbbb",currentUrl);
                let currentPage = currentUrl.split('/#')[1].replace('#', '');
                console.log("aaaaaaaaaaaaa",currentPage);
                this.currentMenu = currentPage;
            },

        }
    }

</script>

<style scoped>
    .home {
        height: 100%;
        width: 100%;
    }

    .homeHeader {
        width: 100%;
    }

    .el-header {
        padding: 0;
    }

    .homeContainer {
        height: 100%;
        width: 100%;
    }

    .homeContainer .homeAside {
        background: #545c64;

    }

    .el-menu {
        border-right: solid 0px #e6e6e6;
    }


</style>
