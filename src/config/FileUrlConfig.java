package config;

/**
 * 文件操作路径配置（文件服务器）
 * @author gql
 *
 */
public class FileUrlConfig {

	/** 文件访问根地址 */
	public static final String file_visit_url = "http://www.nverzuida.com/pandapayFileservice";
	
	/** 文件服务器操作凭证 */
	public static final String file_remote_token = "da3dfgdfg5ytvr_djuyj35345tyn777nu@iqmkj";
	
	/** 文件服务器上传文件访问地址 */
	public static final String file_remote_upload_url = file_visit_url + "/fileService/upload";
	
	/** 文件服务器删除文件访问地址 */
	public static final String file_remote_delete_url = file_visit_url + "/fileService/delete";
	
	/** 安卓安装包目录*/
	public static final String file_remote_apk_url = "apk";
	
	/** 导出excel文件目录*/
	public static final String file_local_excel_url = "downLoadexcel";
	
	/** 首页广告图片目录 */
	public static final String file_remote_ads_url = "adsImg";
	
	/** 实名认证图片目录 */
	public static final String file_user_authen_url = "userAuthenImg";
	
}
