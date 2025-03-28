<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','bidding_project') || $check_field('add','bidding_project') || $check_field('set','bidding_project')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="招标项目" prop="bidding_project">
												<el-input id="bidding_project" v-model="form['bidding_project']" placeholder="请输入招标项目"
							  v-if="user_group === '管理员' || (form['result_publicity_id'] && $check_field('set','bidding_project')) || (!form['result_publicity_id'] && $check_field('add','bidding_project'))" :disabled="disabledObj['bidding_project_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','bidding_project')">{{form['bidding_project']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','bidding_classification') || $check_field('add','bidding_classification') || $check_field('set','bidding_classification')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="招标分类" prop="bidding_classification">
												<el-input id="bidding_classification" v-model="form['bidding_classification']" placeholder="请输入招标分类"
							  v-if="user_group === '管理员' || (form['result_publicity_id'] && $check_field('set','bidding_classification')) || (!form['result_publicity_id'] && $check_field('add','bidding_classification'))" :disabled="disabledObj['bidding_classification_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','bidding_classification')">{{form['bidding_classification']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','tendering_unit') || $check_field('add','tendering_unit') || $check_field('set','tendering_unit')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="招标单位" prop="tendering_unit">
													<el-select v-if="user_group === '管理员' || (form['result_publicity_id'] && $check_field('set','tendering_unit')) || (!form['result_publicity_id'] && $check_field('add','tendering_unit'))" id="tendering_unit" v-model="form['tendering_unit']" :disabled="disabledObj['tendering_unit_isDisabled']">
							<el-option v-for="o in list_user_tendering_unit" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','tendering_unit')" id="tendering_unit" v-model="form['tendering_unit']" :disabled="true">
							<el-option v-for="o in list_user_tendering_unit" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','responsible_unit') || $check_field('add','responsible_unit') || $check_field('set','responsible_unit')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="责任单位" prop="responsible_unit">
												<el-input id="responsible_unit" v-model="form['responsible_unit']" placeholder="请输入责任单位"
							  v-if="user_group === '管理员' || (form['result_publicity_id'] && $check_field('set','responsible_unit')) || (!form['result_publicity_id'] && $check_field('add','responsible_unit'))" :disabled="disabledObj['responsible_unit_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','responsible_unit')">{{form['responsible_unit']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','tender_cover') || $check_field('add','tender_cover') || $check_field('set','tender_cover')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="招标封面" prop="tender_cover">
								<el-upload :disabled="disabledObj['tender_cover_isDisabled']" id="tender_cover" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_tender_cover"
						:show-file-list="false" v-if="user_group === '管理员' || (form['result_publicity_id'] && $check_field('set','tender_cover')) || (!form['result_publicity_id'] && $check_field('add','tender_cover'))">
						<img v-if="form['tender_cover']" :src="$fullUrl(form['tender_cover'])" class="avatar">
						<i v-else class="el-icon-plus avatar-uploader-icon"></i>
					</el-upload>
					<el-image v-else-if="$check_field('get','tender_cover')" style="width: 100px; height: 100px"
						:src="$fullUrl(form['tender_cover'])" :preview-src-list="[$fullUrl(form['tender_cover'])]">
						<div slot="error" class="image-slot">
							<img src="../../../public/img/error.png" style="width: 90px; height: 90px" />
						</div>
					</el-image>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','bidding_merchant') || $check_field('add','bidding_merchant') || $check_field('set','bidding_merchant')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="投标商家" prop="bidding_merchant">
													<el-select v-if="user_group === '管理员' || (form['result_publicity_id'] && $check_field('set','bidding_merchant')) || (!form['result_publicity_id'] && $check_field('add','bidding_merchant'))" id="bidding_merchant" v-model="form['bidding_merchant']" :disabled="disabledObj['bidding_merchant_isDisabled']">
							<el-option v-for="o in list_user_bidding_merchant" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
						<el-select v-else-if="$check_field('get','bidding_merchant')" id="bidding_merchant" v-model="form['bidding_merchant']" :disabled="true">
							<el-option v-for="o in list_user_bidding_merchant" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','supplier') || $check_field('add','supplier') || $check_field('set','supplier')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="供应商家" prop="supplier">
												<el-input id="supplier" v-model="form['supplier']" placeholder="请输入供应商家"
							  v-if="user_group === '管理员' || (form['result_publicity_id'] && $check_field('set','supplier')) || (!form['result_publicity_id'] && $check_field('add','supplier'))" :disabled="disabledObj['supplier_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','supplier')">{{form['supplier']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','bid_amount') || $check_field('add','bid_amount') || $check_field('set','bid_amount')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="投标金额" prop="bid_amount">
								<el-input-number id="bid_amount" v-model.number="form['bid_amount']"
						v-if="user_group === '管理员' || (form['result_publicity_id'] && $check_field('set','bid_amount')) || (!form['result_publicity_id'] && $check_field('add','bid_amount'))" :disabled="disabledObj['bid_amount_isDisabled']"></el-input-number>
					<div v-else-if="$check_field('get','bid_amount')">{{form['bid_amount']}}</div>
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
				field: "result_publicity_id",
				url_add: "~/api/result_publicity/add?",
				url_set: "~/api/result_publicity/set?",
				url_get_obj: "~/api/result_publicity/get_obj?",
				url_upload: "~/api/result_publicity/upload?",

				query: {
					"result_publicity_id": 0,
				},

				form: {
								"bidding_project":  '', // 招标项目
										"bidding_classification":  '', // 招标分类
										"tendering_unit": 0, // 招标单位
										"responsible_unit":  '', // 责任单位
										"tender_cover":  '', // 招标封面
										"bidding_merchant": 0, // 投标商家
										"supplier":  '', // 供应商家
										"bid_amount":  0, // 投标金额
											"result_publicity_id": 0, // ID
						
				},
				disabledObj:{
								"bidding_project_isDisabled": false,
										"bidding_classification_isDisabled": false,
										"tendering_unit_isDisabled": false,
										"responsible_unit_isDisabled": false,
										"tender_cover_isDisabled": false,
										"bidding_merchant_isDisabled": false,
										"supplier_isDisabled": false,
					          			"bid_amount_isDisabled": false,
										},

	
		
		
					// 用户列表
				list_user_tendering_unit: [],
				
		
		
					// 用户列表
				list_user_bidding_merchant: [],
				
		
	
			}
		},
		methods: {


	
	
			
	
			
	
				/**
			 * 获取责任单位用户列表
			 */
			async get_list_user_tendering_unit() {
                // if(this.user_group !== "管理员" && this.form["tendering_unit"] === 0) {
                //     this.form["tendering_unit"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=责任单位");
                if(json.result && json.result.list){
                    this.list_user_tendering_unit = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					get_user_tendering_unit(id){
				var obj = this.list_user_tendering_unit.getObj({"user_id":id});
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
			 * 上传招标封面
			 * @param {Object} param 图片参数
			 */
			upload_tender_cover(param){
						this.uploadFile(param.file, "tender_cover");
					},
	
	
			
	
				/**
			 * 获取供应商家用户列表
			 */
			async get_list_user_bidding_merchant() {
                // if(this.user_group !== "管理员" && this.form["bidding_merchant"] === 0) {
                //     this.form["bidding_merchant"] = this.user.user_id;
                // }
                var json = await this.$get("~/api/user/get_list?user_group=供应商家");
                if(json.result && json.result.list){
                    this.list_user_bidding_merchant = json.result.list;
                }
                else if(json.error){
                    console.error(json.error);
                }
			},
					get_user_bidding_merchant(id){
				var obj = this.list_user_bidding_merchant.getObj({"user_id":id});
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
														// 获取缓存数据附加
				form = $.db.get("form");
							$.push(this.form ,form);
									
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
																				$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){


																								


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
					bl = this.$check_action('/result_publicity/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/result_publicity/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/result_publicity/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/result_publicity/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/result_publicity/view','get');
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
									this.get_list_user_tendering_unit();
												this.get_list_user_bidding_merchant();
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
