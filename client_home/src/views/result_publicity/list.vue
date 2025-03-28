<template>
	<div class="diy_list page_result_publicity" id="result_publicity_list">
		<div class="warp">
			<div class="container diy_list_container">
				<div class="diy_list_title">
					<div class="col">
						<span class="title">结果公示列表</span>
					</div>
				</div>
				<div class="row diy_list_search">
					<div class="col">
						<!-- 搜索栏 -->
						<div class="view">
							<span class="diy_list_search_title">关键字搜索：</span>
								<b-form-input size="sm" class="mr-sm-2" placeholder="招标项目搜索" v-model="query['bidding_project']" />
											<b-form-input size="sm" class="mr-sm-2" placeholder="责任单位搜索" v-model="query['responsible_unit']" />
											<b-form-input size="sm" class="mr-sm-2" placeholder="供应商家搜索" v-model="query['supplier']" />
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
						<list_result_publicity :list="list" />
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
	import list_result_publicity from "@/components/diy/list_result_publicity.vue";
	import diy_pager from "@/components/diy/diy_pager";
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		components: {
			diy_pager,
			list_result_publicity
		},
		data() {
			return {
				url_get_list: "~/api/result_publicity/get_list?like=0",

				// 查询条件
				query: {
					keyword: "",
					page: 1,
					size: 10,
								"bidding_project": "", // 招标项目
													"responsible_unit": "", // 责任单位
													"supplier": "", // 供应商家
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
						name: "责任单位正序",
						value: "responsible_unit asc",
					},
					{
						name: "责任单位倒序",
						value: "responsible_unit desc",
					},
									{
						name: "供应商家正序",
						value: "supplier asc",
					},
					{
						name: "供应商家倒序",
						value: "supplier desc",
					},
						],

																
			}
		},
		methods: {
      get_list_before(param) {
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
			 * 筛选
			 */
																										/**
			 * 重置
			 */
			reset() {
							this.query.bidding_project = ""
												this.query.responsible_unit = ""
												this.query.supplier = ""
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

		},
		computed: {
		},
		created() {
																		}
	}
</script>

<style>
</style>
