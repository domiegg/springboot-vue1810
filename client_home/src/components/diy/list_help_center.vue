<template>
	<div class="diy_home diy_list diy_help_center" id="diy_help_center_list">
		<!-- 列表 -->
		<div class="diy_view_list list list-x" v-if="show">
			<router-link class="diy_card goods diy_list_box_wrap" v-for="(o, i) in list" :key="i"
				:to="'/help_center/details?help_center_id=' + o['help_center_id']">
				<!-- 图片 -->
				<div class="diy_list_img_box" v-if="imgList.length" >
					<div class="diy_row" v-for="(item,index) in imgList" :key="item+index" v-show="$check_field('get',item.name,'/help_center/details') && +item.is_img_list">
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
						<div class="diy_row" :class="{[item.name]:true}" v-if="$check_field('get',item.name,'/help_center/details') && +item.is_img_list">
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
						<th class="diy_title" v-if="$check_field('get','message_title')">
						留言标题
					</th>
							<th class="diy_title" v-if="$check_field('get','message_merchant')">
						留言商家
					</th>
							<th class="diy_title" v-if="$check_field('get','supplier')">
						供应商家
					</th>
							<th class="diy_title" v-if="$check_field('get','message_date')">
						留言日期
					</th>
							<th class="diy_title" v-if="$check_field('get','message_content')">
						留言内容
					</th>
							<th class="diy_title" v-if="$check_field('get','reply_status')">
						回复状态
					</th>
							<th class="diy_title" v-if="$check_field('get','message_reply')">
						留言回复
					</th>
					</tr>
				<tr class="diy_row" v-for="(o,i) in list" :key="o+i">
						<td class="diy_field diy_text" v-if="$check_field('get','message_title')">
						<span>
							{{ o["message_title"] }}
						</span>
					</td>
							<td class="diy_field diy_uid" v-if="$check_field('get','message_merchant')">
						<span>
							{{ get_user_name('message_merchant',o['message_merchant']) }}
						</span>
					</td>
							<td class="diy_field diy_text" v-if="$check_field('get','supplier')">
						<span>
							{{ o["supplier"] }}
						</span>
					</td>
							<td class="diy_field diy_date" v-if="$check_field('get','message_date')">
						<span>
							{{ $toTime(o["message_date"] ,"yyyy-MM-dd") }}
						</span>
					</td>
							<td class="diy_field diy_text" v-if="$check_field('get','message_content')">
						<span>
							{{ o["message_content"] }}
						</span>
					</td>
							<td class="diy_field diy_text" v-if="$check_field('get','reply_status')">
						<span>
							{{ o["reply_status"] }}
						</span>
					</td>
							<td class="diy_field diy_text" v-if="$check_field('get','message_reply')">
						<span>
							{{ o["message_reply"] }}
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
						],
						itemList: [
								{
									title: "留言标题",
									name: "message_title",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "留言商家",
									name: "message_merchant",
									type: "UID",
									is_img_list: "0"
								},
								{
									title: "供应商家",
									name: "supplier",
									type: "文本",
									is_img_list: "0"
								},
								{
									title: "留言日期",
									name: "message_date",
									type: "日期",
									is_img_list: "0"
								},
								{
									title: "回复状态",
									name: "reply_status",
									type: "下拉",
									is_img_list: "0"
								},
						],
						richList: [
								{
									title: "留言内容",
									name: "message_content",
									type: "多文本"
								},
								{
									title: "留言回复",
									name: "message_reply",
									type: "多文本"
								},
						],
						// 用户列表
				list_user_message_merchant: [],
									};
		},
		methods: {
			get_user_name(name,id){
				var obj = null;
						if (name == 'message_merchant'){
					obj = this.list_user_message_merchant.getObj({"user_id":id});
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
			 * 获取供应商家用户列表
			 */
			async get_list_user_message_merchant() {
				var json = await this.$get("~/api/user/get_list?user_group=供应商家");
				if(json.result && json.result.list){
					this.list_user_message_merchant = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
								},
		created() {
					this.get_list_user_message_merchant();
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

