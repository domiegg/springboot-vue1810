<template>
	<div class="diy_home diy_list diy_result_publicity" id="diy_result_publicity_list">
		<!-- 列表 -->
		<div class="diy_view_list list list-x" v-if="show">
			<router-link class="diy_card goods diy_list_box_wrap" v-for="(o, i) in list" :key="i"
				:to="'/result_publicity/details?result_publicity_id=' + o['result_publicity_id']">
				<!-- 图片 -->
				<div class="diy_list_img_box" v-if="imgList.length" >
					<div class="diy_row" v-for="(item,index) in imgList" :key="item+index" v-show="$check_field('get',item.name,'/result_publicity/details') && +item.is_img_list">
						<div class="diy_title diy_list_img_title">
							<span>{{item.title}}:</span>
						</div>
						<div class="diy_field diy_img">
							<img :src="$fullUrl(o[item.name])" style="width:100%;height:100%" />
						</div>
					</div>
				</div>
				<!-- 内容 -->
				<div class="diy_list_item_box">
					<div class="diy_list_item_content" v-for="(item,index) in showItemList" :key="item+index">
						<div class="diy_row" :class="{[item.name]:true}" v-if="$check_field('get',item.name,'/result_publicity/details') && +item.is_img_list">
							<div class="diy_title">
								<span>{{item.title}}:</span>
							</div>
							<div class="diy_field diy_text">
								<span v-if="item.type == 'UID'" v-text="get_user_name(item.name,o[item.name])"></span>
								<span v-else-if="item.type == '日期'" v-text="$toTime(o[item.name],'yyyy-MM-dd')"></span>
								<span v-else-if="item.type == '时间'" v-text="$toTime(o[item.name],'hh:mm:ss')"></span>
								<span v-else-if="item.type == '日长'" v-text="$toTime(o[item.name],'yyyy-MM-dd hh:mm:ss')"></span>
								<span v-else v-text="o[item.name]"></span>
							</div>
						</div>
					</div>
				</div>
			</router-link>
		</div>
		<!-- 表格 -->
		<div class="diy_view_table" v-else>
			<table class="diy_table">
				<tr class="diy_row">
						<th class="diy_title" v-if="$check_field('get','bidding_project')">
						招标项目
					</th>
							<th class="diy_title" v-if="$check_field('get','bidding_classification')">
						招标分类
					</th>
							<th class="diy_title" v-if="$check_field('get','tendering_unit')">
						招标单位
					</th>
							<th class="diy_title" v-if="$check_field('get','responsible_unit')">
						责任单位
					</th>
							<th class="diy_title" v-if="$check_field('get','tender_cover')">
						招标封面
					</th>
							<th class="diy_title" v-if="$check_field('get','bidding_merchant')">
						投标商家
					</th>
							<th class="diy_title" v-if="$check_field('get','supplier')">
						供应商家
					</th>
							<th class="diy_title" v-if="$check_field('get','bid_amount')">
						投标金额
					</th>
					</tr>
				<tr class="diy_row" v-for="(o,i) in list" :key="o+i">
						<td class="diy_field diy_text" v-if="$check_field('get','bidding_project')">
						<span>
							{{ o["bidding_project"] }}
						</span>
					</td>
							<td class="diy_field diy_text" v-if="$check_field('get','bidding_classification')">
						<span>
							{{ o["bidding_classification"] }}
						</span>
					</td>
							<td class="diy_field diy_uid" v-if="$check_field('get','tendering_unit')">
						<span>
							{{ get_user_name('tendering_unit',o['tendering_unit']) }}
						</span>
					</td>
							<td class="diy_field diy_text" v-if="$check_field('get','responsible_unit')">
						<span>
							{{ o["responsible_unit"] }}
						</span>
					</td>
							<td class="diy_field" v-if="$check_field('get','tender_cover')">
						<img class="diy_img" :src="o['tender_cover']" />
					</td>
							<td class="diy_field diy_uid" v-if="$check_field('get','bidding_merchant')">
						<span>
							{{ get_user_name('bidding_merchant',o['bidding_merchant']) }}
						</span>
					</td>
							<td class="diy_field diy_text" v-if="$check_field('get','supplier')">
						<span>
							{{ o["supplier"] }}
						</span>
					</td>
							<td class="diy_field diy_number" v-if="$check_field('get','bid_amount')">
						<span>
							{{ o["bid_amount"] }}
						</span>
					</td>
					</tr>
			</table>
		</div>
	</div>
</template>

<script>
	export default {
		props: {
			list: {
				type: Array,
				default: function() {
					return [];
				},
			},
			show: {
				type: Boolean,
				default: function(){
					return true;
				}
			}
		},
		data() {
			return {
						imgList: [
						{
							title: "招标封面",
							name: "tender_cover",
							type: "图片",
							is_img_list: "1"
						},
						],
						itemList: [
								{
									title: "招标项目",
									name: "bidding_project",
									type: "文本",
									is_img_list: "1"
								},
								{
									title: "招标分类",
									name: "bidding_classification",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "招标单位",
									name: "tendering_unit",
									type: "UID",
									is_img_list: "0"
								},
								{
									title: "责任单位",
									name: "responsible_unit",
									type: "文本",
									is_img_list: "1"
								},
								{
									title: "投标商家",
									name: "bidding_merchant",
									type: "UID",
									is_img_list: "0"
								},
								{
									title: "供应商家",
									name: "supplier",
									type: "文本",
									is_img_list: "1"
								},
								{
									title: "投标金额",
									name: "bid_amount",
									type: "数字",
									is_img_list: "0"
								},
						],
						richList: [
						],
							// 用户列表
				list_user_tendering_unit: [],
								// 用户列表
				list_user_bidding_merchant: [],
						};
		},
		methods: {
			get_user_name(name,id){
				var obj = null;
							if (name == 'tendering_unit'){
					obj = this.list_user_tendering_unit.getObj({"user_id":id});
				}
								if (name == 'bidding_merchant'){
					obj = this.list_user_bidding_merchant.getObj({"user_id":id});
				}
							var ret = "";
				if(obj){
					ret = obj.nickname+"-"+obj.username;
					// if(obj.nickname){
					// 	ret = obj.nickname;
					// }
					// else{
					// 	ret = obj.username;
					// }
				}
				return ret;
			},
						/**
			 * 获取责任单位用户列表
			 */
			async get_list_user_tendering_unit() {
				var json = await this.$get("~/api/user/get_list?user_group=责任单位");
				if(json.result && json.result.list){
					this.list_user_tendering_unit = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
							/**
			 * 获取供应商家用户列表
			 */
			async get_list_user_bidding_merchant() {
				var json = await this.$get("~/api/user/get_list?user_group=供应商家");
				if(json.result && json.result.list){
					this.list_user_bidding_merchant = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
					},
		created() {
						this.get_list_user_tendering_unit();
							this.get_list_user_bidding_merchant();
					},
		computed:{
			showItemList(){
				let arr = [];
				let _type = ["视频","音频","文件"];
				this.itemList.forEach(item => {
					if(_type.indexOf(item.type) === -1 && !!+item.is_img_list){
						arr.push(item)
					}
				})
				return arr.slice(0,4);
			}
		}
	};
</script>

<style scoped>
	.media {
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		flex-basis: 75%;
		min-height: 10rem;
	}

	.goods {
		display: flex;
		width: calc(25% - 1rem);
		margin: 0.5rem;
		padding: 0.5rem;
		flex-direction: column;
		justify-content: space-between;
		background-color: white;
		border-radius: 0.5rem;
	}

	.goods:hover {
		border: 0.2rem solid #909399;
		box-shadow: 0 0.1rem 0.3rem rgba(0, 0, 0, 0.15);
	}

	.goods:hover img {
		filter: blur(1px);
	}

	.price {
		font-size: 1rem;
		margin-right: 3px;
	}

	.price_ago {
		text-decoration: line-through;
		font-size: 0.5rem;
		color: #999;

	}

	.title {
		word-break: break-all;
		overflow: hidden;
		text-overflow: ellipsis;
		white-space: nowrap;
		font-weight: 700;
		padding: .25rem;
	}

	.icon_cart {
		color: #FF5722;
		float: right;
	}

	@media (max-width: 992px) {

		.goods {
			width: calc(33% - 1rem);
			;
		}

	}

	@media (max-width: 768px) {

		.goods {
			width: calc(50% - 1rem);
			;
		}

	}
</style>

