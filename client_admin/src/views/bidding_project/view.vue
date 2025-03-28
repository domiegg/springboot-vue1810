<template>
	<el-main class="bg edit_wrap">
		<el-form ref="form" :model="form" status-icon label-width="120px" v-if="is_view()">

							<el-col v-if="user_group === '管理员' || $check_field('get','bidding_project') || $check_field('add','bidding_project') || $check_field('set','bidding_project')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="招标项目" prop="bidding_project">
												<el-input id="bidding_project" v-model="form['bidding_project']" placeholder="请输入招标项目"
							  v-if="user_group === '管理员' || (form['bidding_project_id'] && $check_field('set','bidding_project')) || (!form['bidding_project_id'] && $check_field('add','bidding_project'))" :disabled="disabledObj['bidding_project_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','bidding_project')">{{form['bidding_project']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','bidding_classification') || $check_field('add','bidding_classification') || $check_field('set','bidding_classification')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="招标分类" prop="bidding_classification">
								<el-select id="bidding_classification" v-model="form['bidding_classification']"
						v-if="user_group === '管理员' || (form['bidding_project_id'] && $check_field('set','bidding_classification')) || (!form['bidding_project_id'] && $check_field('add','bidding_classification'))">
						<el-option v-for="o in list_bidding_classification" :key="o['bidding_classification']" :label="o['bidding_classification']"
							:value="o['bidding_classification']">
						</el-option>
					</el-select>
					<div v-else-if="$check_field('get','bidding_classification')">{{form['bidding_classification']}}</div>
							</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','tendering_unit') || $check_field('add','tendering_unit') || $check_field('set','tendering_unit')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="招标单位" prop="tendering_unit">
																		<div v-if="user_group !== '管理员'">
							{{ get_user_session_tendering_unit(form['tendering_unit']) }}
							<!--<el-input id="business_name" v-model="form['tendering_unit']" placeholder="请输入招标单位"-->
							<!--v-if="user_group === '管理员' || (form['bidding_project_id'] && $check_field('set','tendering_unit')) || (!form['bidding_project_id'] && $check_field('add','tendering_unit'))" :disabled="disabledObj['tendering_unit_isDisabled']"></el-input>-->
							<!--<div v-else-if="$check_field('get','tendering_unit')">{{form['tendering_unit']}}</div>-->
							<el-select v-if="user_group === '管理员' || (form['bidding_project_id'] && $check_field('set','tendering_unit')) || (!form['bidding_project_id'] && $check_field('add','tendering_unit'))" id="tendering_unit" v-model="form['tendering_unit']" :disabled="disabledObj['tendering_unit_isDisabled']">
								<el-option v-for="o in list_user_tendering_unit" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
							<el-select v-else-if="$check_field('get','tendering_unit')" id="tendering_unit" v-model="form['tendering_unit']" :disabled="true">
								<el-option v-for="o in list_user_tendering_unit" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
										   :value="o['user_id']">
								</el-option>
							</el-select>
						</div>
						<el-select v-else id="tendering_unit" v-model="form['tendering_unit']" :disabled="disabledObj['tendering_unit_isDisabled']">
							<el-option v-for="o in list_user_tendering_unit" :key="o['username']" :label="o['nickname'] + '-' + o['username']"
									   :value="o['user_id']">
							</el-option>
						</el-select>
																</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','responsible_unit') || $check_field('add','responsible_unit') || $check_field('set','responsible_unit')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="责任单位" prop="responsible_unit">
												<el-input id="responsible_unit" v-model="form['responsible_unit']" placeholder="请输入责任单位"
							  v-if="user_group === '管理员' || (form['bidding_project_id'] && $check_field('set','responsible_unit')) || (!form['bidding_project_id'] && $check_field('add','responsible_unit'))" :disabled="disabledObj['responsible_unit_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','responsible_unit')">{{form['responsible_unit']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','work_address') || $check_field('add','work_address') || $check_field('set','work_address')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="单位地址" prop="work_address">
												<el-input id="work_address" v-model="form['work_address']" placeholder="请输入单位地址"
							  v-if="user_group === '管理员' || (form['bidding_project_id'] && $check_field('set','work_address')) || (!form['bidding_project_id'] && $check_field('add','work_address'))" :disabled="disabledObj['work_address_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','work_address')">{{form['work_address']}}</div>
											</el-form-item>
			</el-col>
								<el-col v-if="user_group === '管理员' || $check_field('get','tender_cover') || $check_field('add','tender_cover') || $check_field('set','tender_cover')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="招标封面" prop="tender_cover">
								<el-upload :disabled="disabledObj['tender_cover_isDisabled']" id="tender_cover" class="avatar-uploader" drag
						accept="image/gif, image/jpeg, image/png, image/jpg" action="" :http-request="upload_tender_cover"
						:show-file-list="false" v-if="user_group === '管理员' || (form['bidding_project_id'] && $check_field('set','tender_cover')) || (!form['bidding_project_id'] && $check_field('add','tender_cover'))">
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
								<el-col v-if="user_group === '管理员' || $check_field('get','introduction_to_bidding') || $check_field('add','introduction_to_bidding') || $check_field('set','introduction_to_bidding')" :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="招标简介" prop="introduction_to_bidding">
								<el-input type="textarea" id="introduction_to_bidding" v-model="form['introduction_to_bidding']" placeholder="请输入招标简介"
						v-if="user_group === '管理员' || (form['bidding_project_id'] && $check_field('set','introduction_to_bidding')) || (!form['bidding_project_id'] && $check_field('add','introduction_to_bidding'))" :disabled="disabledObj['introduction_to_bidding_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','introduction_to_bidding')">{{form['introduction_to_bidding']}}</div>
							</el-form-item>
			</el-col>
					
	
				<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="计时器标题" prop="timer_title">
					<el-input id="timer_title" v-model="form['timer_title']" placeholder="请输入计时器标题"
							  v-if="user_group === '管理员' || $check_action('/bidding_project/view','set') || $check_action('/bidding_project/view','add')" :disabled="disabledObj['timer_title_isDisabled']"></el-input>
					<div v-else-if="$check_field('get','timer_title')">{{form['timer_title']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="计时开始时间" prop="timing_start_time">
					<el-date-picker id="timing_start_time" v-model="form['timing_start_time']" placeholder="选择计时器开始时间"
						v-if="user_group === '管理员' || $check_action('/bidding_project/view','set') || $check_action('/bidding_project/view','add')" :disabled="disabledObj['timing_start_time_isDisabled']" type="datetime" >
					</el-date-picker>
					<div v-else-if="$check_field('get','timing_start_time')">{{form['timing_start_time']}}</div>
				</el-form-item>
			</el-col>
			<el-col :xs="24" :sm="12" :lg="8" class="el_form_item_warp">
				<el-form-item label="计时结束时间" prop="timing_end_time">
					<el-date-picker id="timing_end_time" v-model="form['timing_end_time']" placeholder="选择计时器开始时间"
						v-if="user_group === '管理员' || $check_action('/bidding_project/view','set') || $check_action('/bidding_project/view','add')" :disabled="disabledObj['timing_end_time_isDisabled']" type="datetime" >
					</el-date-picker>
					<div v-else-if="$check_field('get','timing_end_time')">{{form['timing_end_time']}}</div>
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
				field: "bidding_project_id",
				url_add: "~/api/bidding_project/add?",
				url_set: "~/api/bidding_project/set?",
				url_get_obj: "~/api/bidding_project/get_obj?",
				url_upload: "~/api/bidding_project/upload?",

				query: {
					"bidding_project_id": 0,
				},

				form: {
								"bidding_project":  '', // 招标项目
										"bidding_classification":  '', // 招标分类
										"tendering_unit": 0, // 招标单位
										"responsible_unit":  '', // 责任单位
										"work_address":  '', // 单位地址
										"tender_cover":  '', // 招标封面
										"introduction_to_bidding":  '', // 招标简介
											"bidding_project_id": 0, // ID
									"timer_title":'', // 计时器标题
					"timing_start_time":'', // 计时开始时间
					"timing_end_time":'', // 计时结束时间
			
				},
				disabledObj:{
								"bidding_project_isDisabled": false,
										"bidding_classification_isDisabled": false,
										"tendering_unit_isDisabled": false,
										"responsible_unit_isDisabled": false,
										"work_address_isDisabled": false,
										"tender_cover_isDisabled": false,
										"introduction_to_bidding_isDisabled": false,
										"timer_title_isDisabled": false,
					"timing_start_time_isDisabled": false,
					"timing_end_time_isDisabled": false,
						},

	
						// 招标分类选项列表
				list_bidding_classification: [""],
	
		
					// 用户列表
				list_user_tendering_unit: [],
						// 用户组
				group_user_tendering_unit: "",
				
		
		
		
	
			}
		},
		methods: {


	
	
			
				/**
			 * 获取招标分类列表
			 */
			async get_list_bidding_classification() {
				var json = await this.$get("~/api/bidding_classification/get_list?");
				if(json.result && json.result.list){
					this.list_bidding_classification = json.result.list;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
	
			
	
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
					/**
			 * 获取责任单位用户组
			 */
			async get_group_user_tendering_unit() {
							this.form["tendering_unit"] = this.user.user_id;
							var json = await this.$get("~/api/user_group/get_obj?name=责任单位");
				if(json.result && json.result.obj){
					this.group_user_tendering_unit = json.result.obj;
				}
				else if(json.error){
					console.error(json.error);
				}
			},
			get_user_session_tendering_unit(id){
				var _this = this;
				var user_id = {"user_id":id}
				var url = "~/api/"+_this.group_user_tendering_unit.source_table+"/get_obj?"
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
												_this.form["tendering_unit"] = id
									_this.disabledObj['tendering_unit' + '_isDisabled'] = true
						for (var i=0;i<arr.length;i++){
						  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
							for (var j = 0; j < arrForm.length; j++) {
							  if (arr[i] === arrForm[j]) {
								if (arr[i] !== "tendering_unit") {
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
																		$.db.del("form");
				return param;
			},

			/**
			 * 获取对象之后
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json, func){


																					
				if (this.form["timing_end_time"] && JSON.stringify(this.form["timing_end_time"]).indexOf("-")===-1) {
					this.form["timing_end_time"] = this.$toTime(parseInt(this.form["timing_end_time"]), "yyyy-MM-dd hh:mm:ss")
				}
				if (this.form["timing_start_time"] && JSON.stringify(this.form["timing_start_time"]).indexOf("-")===-1) {
					this.form["timing_start_time"] = this.$toTime(parseInt(this.form["timing_start_time"]), "yyyy-MM-dd hh:mm:ss")
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
					bl = this.$check_action('/bidding_project/table','add');
					console.log(bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限");
				}
				if(!bl){
					bl = this.$check_action('/bidding_project/table','set');
					console.log(bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限");
				}
				if(!bl){
					bl = this.$check_action('/bidding_project/view','add');
					console.log(bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限");
				}
				if(!bl){
					bl = this.$check_action('/bidding_project/view','set');
					console.log(bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限");
				}
				if(!bl){
					bl = this.$check_action('/bidding_project/view','get');
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
						this.get_list_bidding_classification();
							this.get_list_user_tendering_unit();
					this.get_group_user_tendering_unit();
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
