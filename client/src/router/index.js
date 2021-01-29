import Vue from 'vue'
import VueRouter from 'vue-router'
/* Layout */
import Layout from '../views/layout/Layout'

Vue.use(VueRouter)

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location, onResolve, onReject) {
	if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject)
	return originalPush.call(this, location).catch(err => err)
}

export const constantRouterMap = [
	{path: '/login', component: () => import('@/views/Login'), hidden: true},
	{path: '/404', component: () => import('@/views/404'), hidden: true},
	{
		path: '',
		component: Layout,
		redirect: '/home',
		children: [{
				path: 'home',
				name: 'home',
				component: () => import('@/views/Home'),
				meta: {title: '首页', icon: 'home'}
		}]
	}
]


export const asyncRouterMap = [
  {
    path: '/personnelManagement',
    component: Layout,
    redirect: '/personnelManagement',
    name: 'personnelManagement',
    meta: {title: '人员管理', icon: 'product'},
    children: [{
				path: 'organization',
				name: 'organization',
				component: () => import('@/views/personnelManagement/organization'),
				meta: {title: '组织架构', icon: 'ums-admin'}
				},
				{
			  path: 'roleManagement',
			  name: 'roleManagement',
			  component: () => import('@/views/personnelManagement/roleManagement'),
			  meta: {title: '角色管理', icon: 'ums-admin'}
			  }
    ]
  },
  {path: '*', redirect: '/404', hidden: true}
]

const router = new VueRouter({
  // mode: 'history',
  // base: process.env.BASE_URL,
	scrollBehavior: () => ({y: 0}),
  routes: constantRouterMap
})

export default router


