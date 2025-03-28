<template>
  <div class="page_search">
	<div class="warp">
	  <div class="container">
		<div class="row">
		  <div class="col-12">
			<div class="card_result_search">
			  <div class="title">搜索结果</div>

				<!-- 文章搜索结果 -->
			  <list_result_search
				:list="result_article"
				title="新闻资讯"
				source_table="article"
			  ></list_result_search>


						  <list_result_search
				v-if="$check_action('/supplier/list', 'get')"
				:list="result_supplier_supplier"
				title="供应商家供应商家"
				source_table="supplier"
			  ></list_result_search>
												  <list_result_search
				v-if="$check_action('/responsible_unit/list', 'get')"
				:list="result_responsible_unit_responsible_unit"
				title="责任单位责任单位"
				source_table="responsible_unit"
			  ></list_result_search>
												  <list_result_search
				v-if="$check_action('/bidding_classification/list', 'get')"
				:list="result_bidding_classification_bidding_classification"
				title="招标分类招标分类"
				source_table="bidding_classification"
			  ></list_result_search>
									  <list_result_search
				v-if="$check_action('/bidding_project/list', 'get')"
				:list="result_bidding_project_bidding_project"
				title="招标项目招标项目"
				source_table="bidding_project"
			  ></list_result_search>
								  <list_result_search
				v-if="$check_action('/bidding_project/list', 'get')"
				:list="result_bidding_project_bidding_classification"
				title="招标项目招标分类"
				source_table="bidding_project"
			  ></list_result_search>
											  <list_result_search
				v-if="$check_action('/bidding_project/list', 'get')"
				:list="result_bidding_project_responsible_unit"
				title="招标项目责任单位"
				source_table="bidding_project"
			  ></list_result_search>
																		  <list_result_search
				v-if="$check_action('/tender_information/list', 'get')"
				:list="result_tender_information_bidding_project"
				title="投标信息招标项目"
				source_table="tender_information"
			  ></list_result_search>
														  <list_result_search
				v-if="$check_action('/tender_information/list', 'get')"
				:list="result_tender_information_responsible_unit"
				title="投标信息责任单位"
				source_table="tender_information"
			  ></list_result_search>
														  <list_result_search
				v-if="$check_action('/tender_information/list', 'get')"
				:list="result_tender_information_supplier"
				title="投标信息供应商家"
				source_table="tender_information"
			  ></list_result_search>
															  <list_result_search
				v-if="$check_action('/result_publicity/list', 'get')"
				:list="result_result_publicity_bidding_project"
				title="结果公示招标项目"
				source_table="result_publicity"
			  ></list_result_search>
														  <list_result_search
				v-if="$check_action('/result_publicity/list', 'get')"
				:list="result_result_publicity_responsible_unit"
				title="结果公示责任单位"
				source_table="result_publicity"
			  ></list_result_search>
														  <list_result_search
				v-if="$check_action('/result_publicity/list', 'get')"
				:list="result_result_publicity_supplier"
				title="结果公示供应商家"
				source_table="result_publicity"
			  ></list_result_search>
												  <list_result_search
				v-if="$check_action('/market_supervision/list', 'get')"
				:list="result_market_supervision_title_name"
				title="市场监督标题名称"
				source_table="market_supervision"
			  ></list_result_search>
												  <list_result_search
				v-if="$check_action('/help_center/list', 'get')"
				:list="result_help_center_message_title"
				title="帮助中心留言标题"
				source_table="help_center"
			  ></list_result_search>
														  <list_result_search
				v-if="$check_action('/help_center/list', 'get')"
				:list="result_help_center_message_date"
				title="帮助中心留言日期"
				source_table="help_center"
			  ></list_result_search>
											  <list_result_search
				v-if="$check_action('/help_center/list', 'get')"
				:list="result_help_center_reply_status"
				title="帮助中心回复状态"
				source_table="help_center"
			  ></list_result_search>
									</div>
		  </div>
		</div>
	  </div>
	</div>
  </div>
</template>

<script>
import mixin from "../../mixins/page.js";
import list_result_search from "../../components/diy/list_result_search.vue";

export default {
  mixins: [mixin],
  data() {
	return {
	  "query": {
		word: "",
	  },
	  "result_article": [],
						"result_supplier_supplier":[],
												"result_responsible_unit_responsible_unit":[],
												"result_bidding_classification_bidding_classification":[],
									"result_bidding_project_bidding_project":[],
								"result_bidding_project_bidding_classification":[],
											"result_bidding_project_responsible_unit":[],
																		"result_tender_information_bidding_project":[],
														"result_tender_information_responsible_unit":[],
														"result_tender_information_supplier":[],
															"result_result_publicity_bidding_project":[],
														"result_result_publicity_responsible_unit":[],
														"result_result_publicity_supplier":[],
												"result_market_supervision_title_name":[],
												"result_help_center_message_title":[],
														"result_help_center_message_date":[],
											"result_help_center_reply_status":[],
							};
  },
  methods: {
	/**
	 * 获取文章
	 */
	get_article() {
	  this.$get("~/api/article/get_list?like=0", { page: 1, size: 10, title: this.query.word }, (json) => {
		if (json.result) {
		  this.result_article = json.result.list;
		}
	  });
	},

				/**
	 * 获取supplier
	 */
	get_supplier_supplier(){
		let url = "~/api/supplier/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "supplier": this.query.word }, (json) => {
		  if (json.result) {
			var result_supplier_supplier = json.result.list;
			result_supplier_supplier.map(o => o.title = o['supplier'])
	  			this.result_supplier_supplier = result_supplier_supplier
		 	}
		});
	},
										/**
	 * 获取responsible_unit
	 */
	get_responsible_unit_responsible_unit(){
		let url = "~/api/responsible_unit/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "responsible_unit": this.query.word }, (json) => {
		  if (json.result) {
			var result_responsible_unit_responsible_unit = json.result.list;
			result_responsible_unit_responsible_unit.map(o => o.title = o['responsible_unit'])
	  			this.result_responsible_unit_responsible_unit = result_responsible_unit_responsible_unit
		 	}
		});
	},
										/**
	 * 获取bidding_classification
	 */
	get_bidding_classification_bidding_classification(){
		let url = "~/api/bidding_classification/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "bidding_classification": this.query.word }, (json) => {
		  if (json.result) {
			var result_bidding_classification_bidding_classification = json.result.list;
			result_bidding_classification_bidding_classification.map(o => o.title = o['bidding_classification'])
	  			this.result_bidding_classification_bidding_classification = result_bidding_classification_bidding_classification
		 	}
		});
	},
							/**
	 * 获取bidding_project
	 */
	get_bidding_project_bidding_project(){
		let url = "~/api/bidding_project/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "bidding_project": this.query.word }, (json) => {
		  if (json.result) {
			var result_bidding_project_bidding_project = json.result.list;
			result_bidding_project_bidding_project.map(o => o.title = o['bidding_project'])
	  			this.result_bidding_project_bidding_project = result_bidding_project_bidding_project
		 	}
		});
	},
						/**
	 * 获取bidding_classification
	 */
	get_bidding_project_bidding_classification(){
		let url = "~/api/bidding_project/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "bidding_classification": this.query.word }, (json) => {
		  if (json.result) {
			var result_bidding_project_bidding_classification = json.result.list;
			result_bidding_project_bidding_classification.map(o => o.title = o['bidding_classification'])
	  			this.result_bidding_project_bidding_classification = result_bidding_project_bidding_classification
		 	}
		});
	},
									/**
	 * 获取responsible_unit
	 */
	get_bidding_project_responsible_unit(){
		let url = "~/api/bidding_project/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "responsible_unit": this.query.word }, (json) => {
		  if (json.result) {
			var result_bidding_project_responsible_unit = json.result.list;
			result_bidding_project_responsible_unit.map(o => o.title = o['responsible_unit'])
	  			this.result_bidding_project_responsible_unit = result_bidding_project_responsible_unit
		 	}
		});
	},
																/**
	 * 获取bidding_project
	 */
	get_tender_information_bidding_project(){
		let url = "~/api/tender_information/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "bidding_project": this.query.word }, (json) => {
		  if (json.result) {
			var result_tender_information_bidding_project = json.result.list;
			result_tender_information_bidding_project.map(o => o.title = o['bidding_project'])
	  			this.result_tender_information_bidding_project = result_tender_information_bidding_project
		 	}
		});
	},
												/**
	 * 获取responsible_unit
	 */
	get_tender_information_responsible_unit(){
		let url = "~/api/tender_information/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "responsible_unit": this.query.word }, (json) => {
		  if (json.result) {
			var result_tender_information_responsible_unit = json.result.list;
			result_tender_information_responsible_unit.map(o => o.title = o['responsible_unit'])
	  			this.result_tender_information_responsible_unit = result_tender_information_responsible_unit
		 	}
		});
	},
												/**
	 * 获取supplier
	 */
	get_tender_information_supplier(){
		let url = "~/api/tender_information/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "supplier": this.query.word }, (json) => {
		  if (json.result) {
			var result_tender_information_supplier = json.result.list;
			result_tender_information_supplier.map(o => o.title = o['supplier'])
	  			this.result_tender_information_supplier = result_tender_information_supplier
		 	}
		});
	},
													/**
	 * 获取bidding_project
	 */
	get_result_publicity_bidding_project(){
		let url = "~/api/result_publicity/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "bidding_project": this.query.word }, (json) => {
		  if (json.result) {
			var result_result_publicity_bidding_project = json.result.list;
			result_result_publicity_bidding_project.map(o => o.title = o['bidding_project'])
	  			this.result_result_publicity_bidding_project = result_result_publicity_bidding_project
		 	}
		});
	},
												/**
	 * 获取responsible_unit
	 */
	get_result_publicity_responsible_unit(){
		let url = "~/api/result_publicity/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "responsible_unit": this.query.word }, (json) => {
		  if (json.result) {
			var result_result_publicity_responsible_unit = json.result.list;
			result_result_publicity_responsible_unit.map(o => o.title = o['responsible_unit'])
	  			this.result_result_publicity_responsible_unit = result_result_publicity_responsible_unit
		 	}
		});
	},
												/**
	 * 获取supplier
	 */
	get_result_publicity_supplier(){
		let url = "~/api/result_publicity/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "supplier": this.query.word }, (json) => {
		  if (json.result) {
			var result_result_publicity_supplier = json.result.list;
			result_result_publicity_supplier.map(o => o.title = o['supplier'])
	  			this.result_result_publicity_supplier = result_result_publicity_supplier
		 	}
		});
	},
										/**
	 * 获取title_name
	 */
	get_market_supervision_title_name(){
		let url = "~/api/market_supervision/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "title_name": this.query.word }, (json) => {
		  if (json.result) {
			var result_market_supervision_title_name = json.result.list;
			result_market_supervision_title_name.map(o => o.title = o['title_name'])
	  			this.result_market_supervision_title_name = result_market_supervision_title_name
		 	}
		});
	},
										/**
	 * 获取message_title
	 */
	get_help_center_message_title(){
		let url = "~/api/help_center/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "message_title": this.query.word }, (json) => {
		  if (json.result) {
			var result_help_center_message_title = json.result.list;
			result_help_center_message_title.map(o => o.title = o['message_title'])
	  			this.result_help_center_message_title = result_help_center_message_title
		 	}
		});
	},
												/**
	 * 获取message_date
	 */
	get_help_center_message_date(){
		let url = "~/api/help_center/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "message_date": this.query.word }, (json) => {
		  if (json.result) {
			var result_help_center_message_date = json.result.list;
			result_help_center_message_date.map(o => o.title = o['message_date'])
	  			this.result_help_center_message_date = result_help_center_message_date
		 	}
		});
	},
									/**
	 * 获取reply_status
	 */
	get_help_center_reply_status(){
		let url = "~/api/help_center/get_list?like=0";
				this.$get(url, { page: 1, size: 10, "reply_status": this.query.word }, (json) => {
		  if (json.result) {
			var result_help_center_reply_status = json.result.list;
			result_help_center_reply_status.map(o => o.title = o['reply_status'])
	  			this.result_help_center_reply_status = result_help_center_reply_status
		 	}
		});
	},
						
  },
  components: { list_result_search },
	created(){
    this.query.word = this.$route.query.word || "";
  },
  mounted() {
	this.get_article();
					this.get_supplier_supplier();
											this.get_responsible_unit_responsible_unit();
											this.get_bidding_classification_bidding_classification();
								this.get_bidding_project_bidding_project();
							this.get_bidding_project_bidding_classification();
										this.get_bidding_project_responsible_unit();
																	this.get_tender_information_bidding_project();
													this.get_tender_information_responsible_unit();
													this.get_tender_information_supplier();
														this.get_result_publicity_bidding_project();
													this.get_result_publicity_responsible_unit();
													this.get_result_publicity_supplier();
											this.get_market_supervision_title_name();
											this.get_help_center_message_title();
													this.get_help_center_message_date();
										this.get_help_center_reply_status();
						  },
  watch: {
	$route() {
	  $.push(this.query, this.$route.query);
	  this.get_article();
				  this.get_supplier_supplier();
										  this.get_responsible_unit_responsible_unit();
										  this.get_bidding_classification_bidding_classification();
							  this.get_bidding_project_bidding_project();
						  this.get_bidding_project_bidding_classification();
									  this.get_bidding_project_responsible_unit();
																  this.get_tender_information_bidding_project();
												  this.get_tender_information_responsible_unit();
												  this.get_tender_information_supplier();
													  this.get_result_publicity_bidding_project();
												  this.get_result_publicity_responsible_unit();
												  this.get_result_publicity_supplier();
										  this.get_market_supervision_title_name();
										  this.get_help_center_message_title();
												  this.get_help_center_message_date();
									  this.get_help_center_reply_status();
							},
  },
};
</script>

<style scoped>
.card_search {
  text-align: center;
}
.card_result_search>.title {
  text-align: center;
  padding: 10px 0;
}
</style>
