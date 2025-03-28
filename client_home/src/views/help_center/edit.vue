<template>
	<div class="diy_edit page_help_center" id="help_center_edit">
		<div class='warp'>
			<div class='container'>
				<div class='row diy_edit_content_box'>
						<div v-if="$check_field('set','message_title') || $check_field('add','message_title') || $check_field('get','message_title')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								留言标题:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_message_title" v-model="form['message_title']" placeholder="请输入留言标题" v-if="(form['message_title'] && $check_field('set','message_title')) || (!form['message_title'] && $check_field('add','message_title'))"  :disabled="disabledObj['message_title_isDisabled']"/>
							<span v-else-if="$check_field('get','message_title')">{{ form['${obj.name}'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','message_merchant') || $check_field('add','message_merchant') || $check_field('get','message_merchant')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								留言商家:
							</span>
						</div>
						<div class="diy_field diy_down">
							<select id="form_message_merchant" :disabled="disabledObj['message_merchant_isDisabled']" v-model="form['message_merchant']" v-if="(form['message_merchant'] && $check_field('set','message_merchant')) || (!form['message_merchant'] && $check_field('add','message_merchant'))" >
								<option v-for="o in list_user_message_merchant" :value="o['user_id']">
									{{o['nickname'] + '-' + o['username']}}
								</option>
							</select>
							<span v-else-if="$check_field('get','message_merchant')">{{ form['message_merchant'] }}</span>
						</div>
					</div>
							<div v-if="$check_field('set','supplier') || $check_field('add','supplier') || $check_field('get','supplier')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								供应商家:
							</span>
						</div>
								<!-- 文本 -->
									<div class="diy_field diy_text">
							<input type="text" id="form_supplier" v-model="form['supplier']" placeholder="请输入供应商家" v-if="(form['supplier'] && $check_field('set','supplier')) || (!form['supplier'] && $check_field('add','supplier'))"  :disabled="disabledObj['supplier_isDisabled']"/>
							<span v-else-if="$check_field('get','supplier')">{{ form['${obj.name}'] }}</span>
						</div>
										</div>
							<div v-if="$check_field('set','message_date') || $check_field('add','message_date') || $check_field('get','message_date')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								留言日期:
							</span>
						</div>
								<!-- 日期 -->
						<div class="diy_field diy_date">
							<input type="date" :disabled="disabledObj['message_date_isDisabled']" id="form_message_date" v-model="form['message_date']" placeholder="请输入留言日期" v-if="(form['message_date'] && $check_field('set','message_date')) || (!form['message_date'] && $check_field('add','message_date'))" />
							<span v-else-if="$check_field('get','message_date')">{{ form['${obj.name}'] }}</span>
						</div>
							</div>
							<div v-if="$check_field('set','message_content') || $check_field('add','message_content') || $check_field('get','message_content')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								留言内容:
							</span>
						</div>
								<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_message_content" v-model="form['message_content']" v-if="(form['message_content'] && $check_field('set','message_content')) || (!form['message_content'] && $check_field('add','message_content'))" :disabled="disabledObj['message_content_isDisabled']" />
							<span v-else-if="$check_field('get','message_content')">{{ form['${obj.name}'] }}</span>
						</div>
							</div>
							<div v-if="$check_field('set','reply_status') || $check_field('add','reply_status') || $check_field('get','reply_status')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								回复状态:
							</span>
						</div>
								<!-- 选项 -->
						<div class="diy_field diy_down">
							<select id="form_reply_status" v-model="form['reply_status']" v-if="(form['reply_status'] && $check_field('set','reply_status')) || (!form['reply_status'] && $check_field('add','reply_status'))" >
								<option v-for="o in list_reply_status" :value="o">
									{{o}}
								</option>
							</select>
							<span v-else-if="$check_field('get','reply_status')">{{ form['reply_status'] }}</span>
						</div>
							</div>
							<div v-if="$check_field('set','message_reply') || $check_field('add','message_reply') || $check_field('get','message_reply')" class="form-item col-12 col-md-6">
						<div class="diy_title">
							<span>
								留言回复:
							</span>
						</div>
								<!-- 多文本 -->
						<div class="diy_field diy_desc">
							<textarea id="form_message_reply" v-model="form['message_reply']" v-if="(form['message_reply'] && $check_field('set','message_reply')) || (!form['message_reply'] && $check_field('add','message_reply'))" :disabled="disabledObj['message_reply_isDisabled']" />
							<span v-else-if="$check_field('get','message_reply')">{{ form['${obj.name}'] }}</span>
						</div>
							</div>
	




				</div>
				<div class="diy_edit_submit_box row">
					<div class="col-12">
						<div class="btn_box">
							<button class="btn_submit" @click="submit()">提交</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import mixin from "@/mixins/page.js";
	export default {
		mixins: [mixin],
		components: {},
		data() {
			return {
				url_get_obj: "~/api/help_center/get_obj?",
				url_add: "~/api/help_center/add?",
				url_set: "~/api/help_center/set?",

				// 登录权限
				oauth: {
					"signIn": true,
					"user_group": []
				},

				// 查询条件
				query: {
						"message_title": "",
							"message_merchant": 0,
							"supplier": "",
							"message_date": "",
							"message_content": "",
							"reply_status": "",
							"message_reply": "",
						"help_center_id": 0,
				},

				obj: {
						"message_title":  '', // 留言标题
							"message_merchant": 0, // 留言商家
							"supplier":  '', // 供应商家
							"message_date": new Date().getTime(),
							"message_content":  '', // 留言内容
							"reply_status":  '', // 回复状态
							"message_reply":  '', // 留言回复
						"help_center_id": 0,
				},

				// 表单字段
				form: {
						"message_title":  '', // 留言标题
							"message_merchant": 0, // 留言商家
							"supplier":  '', // 供应商家
							"message_date": new Date().getTime(),
							"message_content":  '', // 留言内容
							"reply_status":  '', // 回复状态
							"message_reply":  '', // 留言回复
						"help_center_id": 0,
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
												// 回复状态选项列表
				list_reply_status: ['待回复','已回复'],
				
				// ID字段
				field: "help_center_id",

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
					async get_user_session_message_merchant(){
				var _this = this;
				var json = await this.$get("~/api/user_group/get_obj?name=供应商家");
				if(json.result && json.result.obj){
					var source_table = json.result.obj.source_table;
					var user_id = _this.$store.state.user.user_id;
					if (user_id){
						var url = "~/api/"+source_table+"/get_obj?"
						this.$get(url, {"user_id":_this.$store.state.user.user_id}, function(res) {
							if (res.result && res.result.obj) {
								var arr = []
								for (let key in res.result.obj) {
									arr.push(key)
								}
								var arrForm = []
								for (let key in _this.form) {
									arrForm.push(key)
								}
								_this.form["message_merchant"] = user_id
								_this.disabledObj['message_merchant' + '_isDisabled'] = true
								for (var i=0;i<arr.length;i++){
                  if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
                    for (var j = 0; j < arrForm.length; j++) {
                      if (arr[i] === arrForm[j]) {
                        if (arr[i] !== "message_merchant") {
                          _this.form[arrForm[j]] = res.result.obj[arr[i]]
                          _this.disabledObj[arrForm[j] + '_isDisabled'] = true
                          break;
                        }
                      }
                    }
                  }
								}
							}
						});
					}
				}
				else if(json.error){
					console.error(json.error);
				}
			},
	
				
				
				
				
				
	
			/**
			 * 修改文件
			 * @param { Object } files 上传文件对象
			 * @param { String } str 表单的属性名
			 */
			change_file(files, str) {
				var form = new FormData();
				form.append("file", files[0]);
				this.$post("~/api/help_center/upload?", form, (res) => {
					if (res.result) {
						this.form[str] = res.result.url;
					} else if (res.error) {
						this.$toast(res.error.message);
					}
				});
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_before(param){
				var form = $.db.get("form");
				// if (form) {
        //   delete(form.examine_state)
        //   delete(form.examine_reply)
        //   this.obj = $.push(this.obj ,form);
				// 	this.form = $.push(this.form ,form);
				// }
				// var arr = []
				// for (let key in form) {
				// 	arr.push(key)
				// }
				// for (var i=0;i<arr.length;i++){
				// 	this.disabledObj[arr[i] + '_isDisabled'] = true
				// }
        if (form) {
          var arr = []
          for (let key in form) {
            arr.push(key)
          }
          var arrForm = []
          for (let key in this.form) {
            arrForm.push(key)
          }
          for (var i=0;i<arr.length;i++){
            if (arr[i]!=='examine_state' && arr[i]!=='examine_reply') {
              for (var j = 0; j < arrForm.length; j++) {
                if (arrForm[j] === arr[i]) {
                  this.form[arrForm[j]] = form[arr[i]]
                  this.obj[arrForm[j]] = form[arr[i]]
                  this.disabledObj[arrForm[j] + '_isDisabled'] = true
                  break;
                }
              }
            }
          }
        }
								        if (JSON.stringify(this.form["message_date"]).indexOf("-")===-1) {
          this.form["message_date"] = this.$toTime(parseInt(this.form["message_date"]), "yyyy-MM-dd")
        }
							
        $.db.del("form");
				return param;
			},

			/**
			 * 获取对象后获取缓存表单
			 * @param {Object} json
			 * @param {Object} func
			 */
			get_obj_after(json ,func){
				// var form = $.db.get("form");
				// var obj = Object.assign({} ,form ,this.obj);
				// if (obj) {
        //   delete(obj.examine_state)
        //   delete(obj.examine_reply)
				// 	this.obj = $.push(this.obj ,obj);
				// }
				// if (form) {
        //   delete(form.examine_state)
        //   delete(form.examine_reply)
				// 	this.form = $.push(this.form ,form);
				// }

				if(func){
					func(json);
				}
			},

		},
		created() {
								this.get_user_session_message_merchant();
					this.get_list_user_message_merchant();
																		},
	}
</script>

<style>




</style>
