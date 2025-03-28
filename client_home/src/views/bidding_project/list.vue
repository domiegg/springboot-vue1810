<template>
	<div class="diy_list page_bidding_project" id="bidding_project_list">
		<div class="warp">
			<div class="container diy_list_container">
				<div class="diy_list_title">
					<div class="col">
						<span class="title">招标项目列表</span>
					</div>
				</div>
				<div class="row diy_list_search">
					<div class="col">
						<!-- 搜索栏 -->
						<div class="view">
							<span class="diy_list_search_title">关键字搜索：</span>
								<b-form-input size="sm" class="mr-sm-2" placeholder="招标项目搜索" v-model="query['bidding_project']" />
									<b-form-input size="sm" class="mr-sm-2" placeholder="招标分类搜索" v-model="query['bidding_classification']" />
										<b-form-input size="sm" class="mr-sm-2" placeholder="责任单位搜索" v-model="query['responsible_unit']" />
											<b-button size="sm" @click="search()" >
								<b-icon icon="search"/>
							</b-button>
						</div>
						<!-- /搜索栏 -->
					</div>
				</div>
				<div class="diy_list_select_box">
					<span class="diy_list_select_title">下拉搜索：</span>
						<div class="diy_list_dropdown_box">
						<div class="col">
							<!-- 筛选 -->
							<div class="view">
															<b-dropdown text="招标分类" variant="outline-dark" left>
									<b-dropdown-item @click="filter_set('全部','bidding_classification')">全部</b-dropdown-item>
										<b-dropdown-item v-for="(o, i) in list_bidding_classification" :key="i" @click="filter_set(o['bidding_classification'],'bidding_classification')" >
												{{ o['bidding_classification'] }}
										</b-dropdown-item>
								</b-dropdown>
																		</div>
							<!-- /筛选 -->
						</div>
					</div>
					<div class="diy_list_sort_box">
						<div class="col">
							<!-- 排序 -->
							<div class="view">
								<b-dropdown text="排序" variant="outline-dark" left>
										<b-dropdown-item v-for="(o, i) in list_sort" :key="i" @click="set_sort(o)" >
												{{ o.name }}
										</b-dropdown-item>
								</b-dropdown>
							</div>
							<!--/排序 -->
						</div>
					</div>
				</div>

				<div class="row diy_list_box">
					<div class="col">
						<!-- 列表 -->
						<list_bidding_project :list="list" />
						<!-- /列表 -->
					</div>
				</div>
				<div class="row diy_list_page_box">
					<div class="col overflow-auto flex_cc">
						<!-- 分页器 -->
<!--						<diy_pager v-model="query['page']" :size="query['size']" :count="count" v-on:toPage="toPage" v-on:toSize="toSize" ></diy_pager>-->
            <b-pagination
                v-model="query.page"
                :total-rows="count"
                :per-page="query.size"
                @change="goToPage"
            />
						<!-- /分页器 -->
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import list_bidding_project from "@/components/diy/list_bidding_project.vue";
	import diy_pager from "@/components/diy/diy_pager";
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		components: {
			diy_pager,
			list_bidding_project
		},
		data() {
			return {
				url_get_list: "~/api/bidding_project/get_list?like=0",

				// 查询条件
				query: {
					keyword: "",
					page: 1,
					size: 10,
								"bidding_project": "", // 招标项目
											"bidding_classification": "", // 招标分类
												"responsible_unit": "", // 责任单位
										},

				// 排序内容
				list_sort: [{
						name: "创建时间从高到低",
						value: "create_time desc",
					},
					{
						name: "创建时间从低到高",
						value: "create_time asc",
					},
					{
						name: "更新时间从高到低",
						value: "update_time desc",
					},
					{
						name: "更新时间从低到高",
						value: "update_time asc",
					},
						{
						name: "招标项目正序",
						value: "bidding_project asc",
					},
					{
						name: "招标项目倒序",
						value: "bidding_project desc",
					},
							{
						name: "招标分类正序",
						value: "bidding_classification asc",
					},
					{
						name: "招标分类倒序",
						value: "bidding_classification desc",
					},
								{
						name: "责任单位正序",
						value: "responsible_unit asc",
					},
					{
						name: "责任单位倒序",
						value: "responsible_unit desc",
					},
								],

							// 招标分类列表
				"list_bidding_classification": [""],
												
			}
		},
		methods: {
      get_list_before(param) {
        var oNowDate = this.dateFormat("yyyy-MM-dd hh:mm:ss")+"";
        var url_get_list = "~/api/bidding_project/get_list?timing_start_time_max=" + oNowDate +
            "&timing_end_time_min=" + oNowDate + "&like=0";
        this.url_get_list= url_get_list;
      },
			/**
			 * 筛选选择
			 */
			filter_set(o,key) {
			    if (o == "全部") {
			        this.query[key] = "";
			    } else {
			        this.query[key] = o;
			    }
			    this.search();
			},

			/**
			 * 排序
			 */
			set_sort(o) {
			    this.query.orderby = o.value;
			    this.search();
			},


					/**
			 * 获取招标分类列表
			 */
			async get_list_bidding_classification() {
				var json = await this.$get("~/api/bidding_classification/get_list?");
				if (json.result) {
					this.list_bidding_classification = json.result.list;
				} else if (json.error) {
					console.log(json.error);
				}
			},
						
			/**
			 * 筛选
			 */
												filter_bidding_classification(o) {
				if (o == "全部") {
					this.query["bidding_classification"] = "";
				} else {
					this.query["bidding_classification"] = o;
				}
				this.search();
			},
																		/**
			 * 重置
			 */
			reset() {
							this.query.bidding_project = ""
										this.query.bidding_classification = ""
											this.query.responsible_unit = ""
										this.search();
			},

			// 返回条数
			toSize(i){
				this.query.size = i;
				this.first();
			},

			// 返回页数
			toPage(i){
				this.query.page = i;
				this.first();
			},

      goToPage(v){
        this.query.page = v;
        this.goToNew(v)
      },

			dateFormat(fmt) {
				var myDate = new Date();
				var o = {
					"M+": myDate.getMonth() + 1, // 月份
					"d+": myDate.getDate(), // 日
					"h+": myDate.getHours(), // 小时
					"m+": myDate.getMinutes(), // 分
					"s+": myDate.getSeconds(), // 秒
					"q+": Math.floor((myDate.getMonth() + 3) / 3), // 季度
					"S": myDate.getMilliseconds() // 毫秒
				};
				if (/(y+)/.test(fmt))
					fmt = fmt.replace(RegExp.$1, (myDate.getFullYear() + "").substr(4 - RegExp.$1.length));
				for (var k in o)
					if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
						return fmt;
			},
		},
		computed: {
		},
		created() {
						/**
			 * 获取招标分类列表
			 */
			this.get_list_bidding_classification();
														}
	}
</script>

<style>
</style>
