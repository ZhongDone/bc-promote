import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
/*import userManage from "../views/system/userManage";*/

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: '登录页',
        component: Login,
        hidden: true
    },
    {
        path: '/Home',
        name: '首页',
        component: Home,
        children: [
            /*{
                path: '/system/userManage',
                name: '首页',
                component: userManage,
            }*/
        ]
    }
]

const router = new VueRouter({
    routes
})

export default router
