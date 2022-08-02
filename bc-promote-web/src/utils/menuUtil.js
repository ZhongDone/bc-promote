import {postRequest} from "./api";
export const initMenu = (router, store) =>{
    console.log(router,'router')
    if(store.state.routes.length>0){
        return;
    }

    postRequest("/eportal/eportal-resource/getMenuTree").then(data=>{
        if(data.content != null){
            let fmtRoutes = formatRoutes(data.content);
            let childrenArr = getChildrenArr(fmtRoutes);
            router.addRoutes([{
                path:'/Home',
                name:'首页',
                component:() => import(`../views/Home.vue`),
                children:childrenArr
            }]);
            // 将数据存入vuex
            store.commit('initRoutes', fmtRoutes);
        }
    });
}

export const formatRoutes = (routes) =>{
    let fmtRoutes = [];
    routes.forEach(router=>{
        let path = router.resourcePath;
        let name = router.resourceName;
        let iconCls = router.resourceIcon;
        let children = [];
        console.log("path", path)
        let fmtRouter = {};
        if(router.children && router.children.length >0){
            children = formatRoutes(router.children);
            fmtRouter = {
                path:path,
                name:name,
                iconCls:iconCls,
                children:children
            }
        }else{
            fmtRouter = {
                path:path,
                name:name,
                iconCls:iconCls,
                component:() => import(`../views${router.resourcePath}.vue`)
            }
        }
        fmtRoutes.push(fmtRouter);
    });
    return fmtRoutes;
};

export const getChildrenArr = (fmtRoutes)=>{
    let childrenArr = [];
    fmtRoutes.forEach(router=>{
        if(router.children && router.children.length >0){
            let arr = getChildrenArr(router.children);
            childrenArr = [...arr,...childrenArr];
        }else{
            childrenArr.push(router);
        }
    });
    return childrenArr;
}
