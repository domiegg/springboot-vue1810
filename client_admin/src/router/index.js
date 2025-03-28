import Vue from 'vue';
import VueRouter from 'vue-router';
import index from '../views/index.vue';
import login from '../views/login.vue';
import forgot from '../views/forgot.vue';
Vue.use(VueRouter)

const routes = [
	// 主页
	{
		path: '/',
		name: 'index',
		component: index,
		meta: {
			index: 0,
			title: '首页'
		}
	},

	// 登录
	{
		path: '/login',
		name: 'login',
		component: login,
		meta: {
			index: 0,
			title: '登录'
		}
	},


	// 忘记密码
	{
		path: '/forgot',
		name: "forgot",
		component: forgot,
		meta: {
			index: 0,
			title: '忘记密码'
		}
	},

	// 修改密码
	{
		path: '/user/password',
		name: "password",
		component: () => import("../views/user/password.vue"),
		meta: {
			index: 0,
			title: '修改密码'
		}
	},

	// 视频播放页
	{
		path: "/media/video",
		name: "video",
		component: () => import('../views/media/video.vue'),
		meta: {
			index: 0,
			title: "视频"
		}
	},

	// 音频播放页
	{
		path: "/media/audio",
		name: "audio",
		component: () => import('../views/media/audio.vue'),
		meta: {
			index: 0,
			title: "音频"
		}
	},



	// 友情链接路由
	// {
	// 	path: '/link/table',
	// 	name: 'link_table',
	// 	component: () => import('../views/link/table.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '链接列表'
	// 	}
	// },
	// {
	// 	path: '/link/view',
	// 	name: 'link_view',
	// 	component: () => import('../views/link/view.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '链接详情'
	// 	}
	// },

	// 轮播图路由
	{
		path: '/slides/table',
		name: 'slides_table',
		component: () => import('../views/slides/table.vue'),
		meta: {
			index: 0,
			title: '轮播图列表'
		}
	},
	{
		path: '/slides/view',
		name: 'slides_view',
		component: () => import('../views/slides/view.vue'),
		meta: {
			index: 0,
			title: '轮播图详情'
		}
	},
		// 文章路由
	{
		path: '/article/table',
		name: 'article_table',
		component: () => import('../views/article/table.vue'),
		meta: {
			index: 0,
			title: '文章列表'
		}
	},
	{
		path: '/article/view',
		name: 'article_view',
		component: () => import('../views/article/view.vue'),
		meta: {
			index: 0,
			title: '文章详情'
		}
	},

	// 文章分类路由
	{
		path: '/article_type/table',
		name: 'article_type_table',
		component: () => import('../views/article_type/table.vue'),
		meta: {
			index: 0,
			title: '文章分类列表'
		}
	},
	{
		path: '/article_type/view',
		name: 'article_type_view',
		component: () => import('../views/article_type/view.vue'),
		meta: {
			index: 0,
			title: '文章分类详情'
		}
	},
	
	// 广告路由
	// {
	// 	path: '/ad/table',
	// 	name: 'ad_table',
	// 	component: () => import('../views/ad/table.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '广告列表'
	// 	}
	// },
	// {
	// 	path: '/ad/view',
	// 	name: 'ad_view',
	// 	component: () => import('../views/ad/view.vue'),
	// 	meta: {
	// 		index: 0,
	// 		title: '广告详情'
	// 	}
	// },



	// 公告路由
	{
		path: '/notice/table',
		name: 'notice_table',
		component: () => import('../views/notice/table.vue'),
		meta: {
			index: 0,
			title: '通知公告列表'
		}
	},
	{
		path: '/notice/view',
		name: 'notice_view',
		component: () => import('../views/notice/view.vue'),
		meta: {
			index: 0,
			title: '通知公告详情'
		}
	},


	// 评论路由
	{
		path: '/comment/table',
		name: 'comment_table',
		component: () => import('../views/comment/table.vue'),
		meta: {
			index: 0,
			title: '评论列表'
		}
	},
	{
		path: '/comment/view',
		name: 'comment_view',
		component: () => import('../views/comment/view.vue'),
		meta: {
			index: 0,
			title: '评论详情'
		}
	},

	// 供应商家路由
	{
		path: '/supplier/table',
		name: 'supplier_table',
		component: () => import('../views/supplier/table.vue'),
		meta: {
			index: 0,
			title: '供应商家列表'
		}
	},
	{
		path: '/supplier/view',
		name: 'supplier_view',
		component: () => import('../views/supplier/view.vue'),
		meta: {
			index: 0,
			title: '供应商家详情'
		}
	},
	// 责任单位路由
	{
		path: '/responsible_unit/table',
		name: 'responsible_unit_table',
		component: () => import('../views/responsible_unit/table.vue'),
		meta: {
			index: 0,
			title: '责任单位列表'
		}
	},
	{
		path: '/responsible_unit/view',
		name: 'responsible_unit_view',
		component: () => import('../views/responsible_unit/view.vue'),
		meta: {
			index: 0,
			title: '责任单位详情'
		}
	},
	// 招标分类路由
	{
		path: '/bidding_classification/table',
		name: 'bidding_classification_table',
		component: () => import('../views/bidding_classification/table.vue'),
		meta: {
			index: 0,
			title: '招标分类列表'
		}
	},
	{
		path: '/bidding_classification/view',
		name: 'bidding_classification_view',
		component: () => import('../views/bidding_classification/view.vue'),
		meta: {
			index: 0,
			title: '招标分类详情'
		}
	},
	// 招标项目路由
	{
		path: '/bidding_project/table',
		name: 'bidding_project_table',
		component: () => import('../views/bidding_project/table.vue'),
		meta: {
			index: 0,
			title: '招标项目列表'
		}
	},
	{
		path: '/bidding_project/view',
		name: 'bidding_project_view',
		component: () => import('../views/bidding_project/view.vue'),
		meta: {
			index: 0,
			title: '招标项目详情'
		}
	},
	// 投标信息路由
	{
		path: '/tender_information/table',
		name: 'tender_information_table',
		component: () => import('../views/tender_information/table.vue'),
		meta: {
			index: 0,
			title: '投标信息列表'
		}
	},
	{
		path: '/tender_information/view',
		name: 'tender_information_view',
		component: () => import('../views/tender_information/view.vue'),
		meta: {
			index: 0,
			title: '投标信息详情'
		}
	},
	// 结果公示路由
	{
		path: '/result_publicity/table',
		name: 'result_publicity_table',
		component: () => import('../views/result_publicity/table.vue'),
		meta: {
			index: 0,
			title: '结果公示列表'
		}
	},
	{
		path: '/result_publicity/view',
		name: 'result_publicity_view',
		component: () => import('../views/result_publicity/view.vue'),
		meta: {
			index: 0,
			title: '结果公示详情'
		}
	},
	// 市场监督路由
	{
		path: '/market_supervision/table',
		name: 'market_supervision_table',
		component: () => import('../views/market_supervision/table.vue'),
		meta: {
			index: 0,
			title: '市场监督列表'
		}
	},
	{
		path: '/market_supervision/view',
		name: 'market_supervision_view',
		component: () => import('../views/market_supervision/view.vue'),
		meta: {
			index: 0,
			title: '市场监督详情'
		}
	},
	// 帮助中心路由
	{
		path: '/help_center/table',
		name: 'help_center_table',
		component: () => import('../views/help_center/table.vue'),
		meta: {
			index: 0,
			title: '帮助中心列表'
		}
	},
	{
		path: '/help_center/view',
		name: 'help_center_view',
		component: () => import('../views/help_center/view.vue'),
		meta: {
			index: 0,
			title: '帮助中心详情'
		}
	},

	// 用户路由
	{
		path: '/user/table',
		name: 'user_table',
		component: () => import('../views/user/table.vue'),
		meta: {
			index: 0,
			title: '用户列表'
		}
	},
	{
		path: '/user/view',
		name: 'user_view',
		component: () => import('../views/user/view.vue'),
		meta: {
			index: 0,
			title: '用户详情'
		}
	},
	{
		path: '/user/info',
		name: 'user_info',
		component: () => import('../views/user/info.vue'),
		meta: {
			index: 0,
			title: '个人信息'
		}
	},
	// 用户组路由
	{
		path: '/user_group/table',
		name: 'user_group_table',
		component: () => import('../views/user_group/table.vue'),
		meta: {
			index: 0,
			title: '用户组列表'
		}
	},
	{
		path: '/user_group/view',
		name: 'user_group_view',
		component: () => import('../views/user_group/view.vue'),
		meta: {
			index: 0,
			title: '用户组详情'
		}
	}
]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

router.beforeEach((to, from, next) => {
	let token = to.query.token;
	if (token){
		$.db.set("token",token,120);
	}
	next();
})

router.afterEach((to, from, next) => {
	let title = "电子招投标系统-admin";
	document.title = title;
})

export default router
