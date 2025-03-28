<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','message_title') || $check_field('add','message_title') || $check_field('set','message_title')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="留言标题" prop="message_title">
												<el-input id="message_title" v-model="form['message_title']" placeholder="请输入留言标题"
							  v-if="user_group === '管理员' || (form['help_center_id'] && $check_field('set','message_title')) || (!form['help_center_id'] && $check_field('add','message_title'))" :disabled="disabledObj['message_title_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','message_title')">{{form['message_title']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','message_merchant') || $check_field('add','message_merchant') || $check_field('set','message_merchant')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="留言商家" prop="message_merchant">
																		<div v-if="user_group !== '管理员'">
							{{ get_user_session_message_merchant(form['message_merchant']) }}
							<!--<el-input id="business_name" v-model="form['message_merchant']" placeholder="请输入留言商家"-->
							<!--v-if="user_group === '管理员' || (form['help_center_id'] && $check_field('set','message_merchant')) || (!form['help_center_id'] && $check_field('add','message_merchant'))" :disabled="disabledObj['message_merchant_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','message_merchant')">{{form['message_merchant']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['help_center_id'] && $check_field('set','message_merchant')) || (!form['help_center_id'] && $check_field('add','message_merchant'))" id="message_merchant" v-model="form['message_merchant']" :disabled="disabledObj['message_merchant_isDisabled']">
								<el-option v-for="o in list_user_message_merchant" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','message_merchant')" id="message_merchant" v-model="form['message_merchant']" :disabled="true">
								<el-option v-for="o in list_user_message_merchant" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="message_merchant" v-model="form['message_merchant']" :disabled="disabledObj['message_merchant_isDisabled']">
							<el-option v-for="o in list_user_message_merchant" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
																</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','supplier') || $check_field('add','supplier') || $check_field('set','supplier')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="供应商家" prop="supplier">
												<el-input id="supplier" v-model="form['supplier']" placeholder="请输入供应商家"
							  v-if="user_group === '管理员' || (form['help_center_id'] && $check_field('set','supplier')) || (!form['help_center_id'] && $check_field('add','supplier'))" :disabled="disabledObj['supplier_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','supplier')">{{form['supplier']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','message_date') || $check_field('add','message_date') || $check_field('set','message_date')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="留言日期" prop="message_date">
								<el-date-picker :disabled="disabledObj['message_date_isDisabled']" v-if="user_group === '管理员' || (form['help_center_id'] && $check_field('set','message_date')) || (!form['help_center_id'] && $check_field('add','message_date'))" id="message_date"
						v-model="form['message_date']" type="date" placeholder="选择日期">
					</el-date-picker>
					<div v-else-if="$check_field('get','message_date')">{{form['message_date']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','message_content') || $check_field('add','message_content') || $check_field('set','message_content')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="留言内容" prop="message_content">
								<el-input type="textarea" id="message_content" v-model="form['message_content']" placeholder="请输入留言内容"
						v-if="user_group === '管理员' || (form['help_center_id'] && $check_field('set','message_content')) || (!form['help_center_id'] && $check_field('add','message_content'))" :disabled="disabledObj['message_content_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','message_content')">{{form['message_content']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','reply_status') || $check_field('add','reply_status') || $check_field('set','reply_status')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="回复状态" prop="reply_status">
								<el-select id="reply_status" v-model="form['reply_status']"
						v-if="user_group === '管理员' || (form['help_center_id'] && $check_field('set','reply_status')) || (!form['help_center_id'] && $check_field('add','reply_status'))">
						<el-option v-for="o in list_reply_status" :key="o" :label="o" :value="o">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','reply_status')">{{form['reply_status']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','message_reply') || $check_field('add','message_reply') || $check_field('set','message_reply')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="留言回复" prop="message_reply">
								<el-input type="textarea" id="message_reply" v-model="form['message_reply']" placeholder="请输入留言回复"
						v-if="user_group === '管理员' || (form['help_center_id'] && $check_field('set','message_reply')) || (!form['help_center_id'] && $check_field('add','message_reply'))" :disabled="disabledObj['message_reply_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','message_reply')">{{form['message_reply']}}</div>
							</el-form-item>
			</el-col>
					
	
	
	
	
	
	
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
				<el-form-item>
					<el-button type="primary" @click="submit()">提交</el-button>
					<el-button @click="cancel()">取消</el-button>
				</el-form-item>
			</el-col>

		</el-form>
	</el-main>
</template>

<script>
	import mixin from "@/mixins/page.js";

	export default {
		mixins: [mixin],
		data() {
			return {
				field: "help_center_id",
				url_add: "~/api/help_center/add?",
				url_set: "~/api/help_center/set?",
				url_get_obj: "~/api/help_center/get_obj?",
				url_upload: "~/api/help_center/upload?",

				query: {
					"help_center_id": 0,
				},

				form: {
								"message_title":  '', // 留言标题
										"message_merchant": 0, // 留言商家
										"supplier":  '', // 供应商家
										"message_date":  '', // 留言日期
										"message_content":  '', // 留言内容
										"reply_status":  '', // 回复状态
										"message_reply":  '', // 留言回复
											"help_center_id": 0, // ID
						
				},
				disabledObj:{
								"message_title_isDisabled": false,
										"message_merchant_isDisabled": false,
										"supplier_isDisabled": false,
										"message_date_isDisabled": false,
										"message_content_isDisabled": false,
										"reply_status_isDisabled": false,
										"message_reply_isDisabled": false,
										},

	
		
					// 用户列表
				list_user_message_merchant: [],
						// 用户组
				group_user_message_merchant: "",
				
		
		
						// 回复状态选项列表
				list_reply_status: ['待回复','已回复'],
	
		
	
			}
		},
		methods: {


	
	
			
	
				/**
			 * 获取供应商家用户列表
			 */
			async get_list_user_message_merchant() {
                // if(this.user_group !== "管理员" && this.form["message_merchant"] === 0) {
                //     this.form["message_merchant"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=供应商家");
                if(json.result && json.result.list){
                    this.list_user_message_merchant = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					/**
			 * 获取供应商家用户组
			 */
			async get_group_user_message_merchant() {
							this.form["message_merchant"] = this.user.user_id;
							var json = await this.$get("~/api/user_group/get_obj?name=供应商家");
				if(json.result && json.result.obj){
					this.group_user_message_merchant = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_message_merchant(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_message_merchant.source_table+"/get_obj?"
				this.$get(url, user_id, function(res) {
					if (res.result && res.result.obj) {
						var arr = []
						for (let key in res.result.obj) {
							arr.push(key)
						}
						var arrForm = []
									for (let key in _this.form) {
							arrForm.push(key)
						}
												_this.form["message_merchant"] = id
									_this.disabledObj['message_merchant' + '_isDisabled'] = true
						for (var i=0;i<arr.length;i++){
						  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
							for (var j = 0; j < arrForm.length; j++) {
							  if (arr[i] === arrForm[j]) {
								if (arr[i] !== "message_merchant") {
			                      _this.form[arrForm[j]] = res.result.obj[arr[i]]
			                      _this.disabledObj[arrForm[j] + '_isDisabled'] = true
								  break;
								} else {
								  _this.disabledObj[arrForm[j] + '_isDisabled'] = true
								}
							  }
							}
						  }
						}
					}
				});
			},
					get_user_message_merchant(id){
				var obj = this.list_user_message_merchant.getObj({"user_id":id});
				var ret = "";
				if(obj){
					if(obj.nickname){
						ret = obj.nickname;}
					else{
						ret = obj.username;
					}
				}
				return ret;
			},
			
	
			
	
			
	
			
	
			
	
		
			/**
			 * 获取对象之前
			 * @param {Object} param
			 */
			get_obj_before(param) {
				var form = "";
														
				if(this.form && form){
					Object.keys(this.form).forEach(key => {
						Object.keys(form).forEach(dbKey => {
							// if(dbKey === "charging_standard"){
							// 	this.form['charging_rules'] = form[dbKey];
							// 	this.disabledObj['charging_rules_isDisabled'] = true;
							// };
							if(key === dbKey){
								this.disabledObj[key+'_isDisabled'] = true;
							}
						})
					})
				}
								        if (this.form["message_date"].indexOf("-")===-1){
          this.form["message_date"] = this.$toTime(parseInt(this.form["message_date"]),"yyyy-MM-dd")
        }
											$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){


											        if(this.form["message_date"]=="0000-00-00"){
          this.form["message_date"] = null;
        }
				if(parseInt(this.form["message_date"]) > 9999){
					this.form["message_date"] = this.$toTime(parseInt(this.form["message_date"]),"yyyy-MM-dd")
				}
											


			},

			/**
			 * 提交前验证事件
			 * @param {Object} 请求参数
			 * @return {String} 验证成功返回null, 失败返回错误提示
			 */
			submit_check(param) {
				let msg = null
																											return msg;
			},

			is_view(){
				var bl = this.user_group == "管理员";

				if(!bl){
					bl = this.$check_action('/help_center/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/help_center/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/help_center/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/help_center/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/help_center/view','get');
					console.log(bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限");
				}

				console.log(bl ? "具有当前页面的查看权，请注意这不代表你有字段的查看权" : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行");

				return bl;
			},
			/**
			 * 上传文件
			 * @param {Object} param
			 */
			uploadimg(param) {
				this.uploadFile(param.file, "avatar");
			},

		},
		created() {
							this.get_list_user_message_merchant();
					this.get_group_user_message_merchant();
															},
	}
</script>

<style>
	.avatar-uploader .el-upload {
		border: 1px dashed #d9d9d9;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
	}

	.avatar-uploader-icon {
		font-size: 28px;
		color: #8c939d;
		width: 178px;
		height: 178px;
		line-height: 178px;
		text-align: center;
	}

	.avatar {
		width: 178px;
		height: 178px;
		display: block;
	}




</style>
