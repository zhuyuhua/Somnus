/*
 * Copyright (c) 2010-2015. Somnus Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.somnus.common.enums;

/**
 * TODO
 * 
 * @author zhuyuhua
 * @version 0.0.1
 */
public enum MIMEType {

	suffix("application/octet-stream"), //
	suffix_323("text/h323"), //
	suffix_acx("application/internet-property-stream"), //
	suffix_ai("application/postscript"), //
	suffix_aif("audio/x-aiff"), //
	suffix_aifc("audio/x-aiff"), //
	suffix_aiff("audio/x-aiff"), //
	suffix_asf("video/x-ms-asf"), //
	suffix_asr("video/x-ms-asf"), //
	suffix_asx("video/x-ms-asf"), //
	suffix_au("audio/basic"), //
	suffix_avi("video/x-msvideo"), //
	suffix_axs("application/olescript"), //
	suffix_bas("text/plain"), //
	suffix_bcpio("application/x-bcpio"), //
	suffix_bin("application/octet-stream"), //
	suffix_bmp("image/bmp"), //
	suffix_c("text/plain"), //
	suffix_cat("application/vnd.ms-pkiseccat"), //
	suffix_cdf("application/x-cdf"), //
	suffix_cer("application/x-x509-ca-cert"), //
	suffix_class("application/octet-stream"), //
	suffix_clp("application/x-msclip"), //
	suffix_cmx("image/x-cmx"), //
	suffix_cod("image/cis-cod"), //
	suffix_cpio("application/x-cpio"), //
	suffix_crd("application/x-mscardfile"), //
	suffix_crl("application/pkix-crl"), //
	suffix_crt("application/x-x509-ca-cert"), //
	suffix_csh("application/x-csh"), //
	suffix_css("text/css"), //
	suffix_dcr("application/x-director"), //
	suffix_der("application/x-x509-ca-cert"), //
	suffix_dir("application/x-director"), //
	suffix_dll("application/x-msdownload"), //
	suffix_dms("application/octet-stream"), //
	suffix_doc("application/msword"), //
	suffix_docx("application/vnd.openxmlformats-officedocument.wordprocessingml.document"), //
	suffix_dot("application/msword"), //
	suffix_dotx("application/vnd.openxmlformats-officedocument.wordprocessingml.template"), //
	suffix_dvi("application/x-dvi"), //
	suffix_dxr("application/x-director"), //
	suffix_eps("application/postscript"), //
	suffix_etx("text/x-setext"), //
	suffix_evy("application/envoy"), //
	suffix_exe("application/octet-stream"), //
	suffix_fif("application/fractals"), //
	suffix_flr("x-world/x-vrml"), //
	suffix_gif("image/gif"), //
	suffix_gtar("application/x-gtar"), //
	suffix_gz("application/x-gzip"), //
	suffix_h("text/plain"), //
	suffix_hdf("application/x-hdf"), //
	suffix_hlp("application/winhlp"), //
	suffix_hqx("application/mac-binhex40"), //
	suffix_hta("application/hta"), //
	suffix_htc("text/x-component"), //
	suffix_htm("text/html"), //
	suffix_html("text/html"), //
	suffix_htt("text/webviewhtml"), //
	suffix_ico("image/x-icon"), //
	suffix_ief("image/ief"), //
	suffix_iii("application/x-iphone"), //
	suffix_ins("application/x-internet-signup"), //
	suffix_isp("application/x-internet-signup"), //
	suffix_jfif("image/pipeg"), //
	suffix_jpe("image/jpeg"), //
	suffix_jpeg("image/jpeg"), //
	suffix_jpg("image/jpeg"), //
	suffix_js("application/x-javascript"), //
	suffix_latex("application/x-latex"), //
	suffix_lha("application/octet-stream"), //
	suffix_lsf("video/x-la-asf"), //
	suffix_lsx("video/x-la-asf"), //
	suffix_lzh("application/octet-stream"), //
	suffix_m13("application/x-msmediaview"), //
	suffix_m14("application/x-msmediaview"), //
	suffix_m3u("audio/x-mpegurl"), //
	suffix_man("application/x-troff-man"), //
	suffix_mdb("application/x-msaccess"), //
	suffix_me("application/x-troff-me"), //
	suffix_mht("message/rfc822"), //
	suffix_mhtml("message/rfc822"), //
	suffix_mid("audio/mid"), //
	suffix_mny("application/x-msmoney"), //
	suffix_mov("video/quicktime"), //
	suffix_movie("video/x-sgi-movie"), //
	suffix_mp2("video/mpeg"), //
	suffix_mp3("audio/mpeg"), //
	suffix_mpa("video/mpeg"), //
	suffix_mpe("video/mpeg"), //
	suffix_mpeg("video/mpeg"), //
	suffix_mpg("video/mpeg"), //
	suffix_mpp("application/vnd.ms-project"), //
	suffix_mpv2("video/mpeg"), //
	suffix_ms("application/x-troff-ms"), //
	suffix_msg("application/vnd.ms-outlook "), //
	suffix_mvb("application/x-msmediaview"), //
	suffix_nc("application/x-netcdf "), //
	suffix_nws("message/rfc822"), //
	suffix_oda("application/oda"), //
	suffix_p10("application/pkcs10"), //
	suffix_p12("application/x-pkcs12"), //
	suffix_p7b("application/x-pkcs7-certificates"), //
	suffix_p7c("application/x-pkcs7-mime"), //
	suffix_p7m("application/x-pkcs7-mime"), //
	suffix_p7r("application/x-pkcs7-certreqresp"), //
	suffix_p7s("application/x-pkcs7-signature"), //
	suffix_pbm("image/x-portable-bitmap"), //
	suffix_pdf("application/pdf"), //
	suffix_pfx("application/x-pkcs12"), //
	suffix_pgm("image/x-portable-graymap"), //
	suffix_pko("application/ynd.ms-pkipko"), //
	suffix_pma("application/x-perfmon"), //
	suffix_pmc("application/x-perfmon"), //
	suffix_pml("application/x-perfmon"), //
	suffix_pmr("application/x-perfmon"), //
	suffix_pmw("application/x-perfmon"), //
	suffix_pnm("image/x-portable-anymap"), //
	suffix_pot("application/vnd.ms-powerpoint"), //
	suffix_potx("application/vnd.openxmlformats-officedocument.presentationml.template"), //
	suffix_ppm("image/x-portable-pixmap"), //
	suffix_pps("application/vnd.ms-powerpoint"), //
	suffix_ppsx("application/vnd.openxmlformats-officedocument.presentationml.slideshow"), //
	suffix_ppt("application/vnd.ms-powerpoint"), //
	suffix_pptx("application/vnd.openxmlformats-officedocument.presentationml.presentation"), //
	suffix_prf("application/pics-rules"), //
	suffix_ps("application/postscript"), //
	suffix_pub("application/x-mspublisher"), //
	suffix_qt("video/quicktime"), //
	suffix_ra("audio/x-pn-realaudio"), //
	suffix_ram("audio/x-pn-realaudio"), //
	suffix_ras("image/x-cmu-raster"), //
	suffix_rgb("image/x-rgb"), //
	suffix_rmi("audio/mid http://www.dreamdu.com"), //
	suffix_roff("application/x-troff"), //
	suffix_rtf("application/rtf"), //
	suffix_rtx("text/richtext"), //
	suffix_scd("application/x-msschedule"), //
	suffix_sct("text/scriptlet"), //
	suffix_setpay("application/set-payment-initiation"), //
	suffix_setreg("application/set-registration-initiation"), //
	suffix_sh("application/x-sh"), //
	suffix_shar("application/x-shar"), //
	suffix_sit("application/x-stuffit"), //
	suffix_sldx("application/vnd.openxmlformats-officedocument.presentationml.slide"), //
	suffix_snd("audio/basic"), //
	suffix_spc("application/x-pkcs7-certificates"), //
	suffix_spl("application/futuresplash"), //
	suffix_src("application/x-wais-source"), //
	suffix_sst("application/vnd.ms-pkicertstore"), //
	suffix_stl("application/vnd.ms-pkistl"), //
	suffix_stm("text/html"), //
	suffix_svg("image/svg+xml"), //
	suffix_sv4cpio("application/x-sv4cpio"), //
	suffix_sv4crc("application/x-sv4crc"), //
	suffix_swf("application/x-shockwave-flash"), //
	suffix_t("application/x-troff"), //
	suffix_tar("application/x-tar"), //
	suffix_tcl("application/x-tcl"), //
	suffix_tex("application/x-tex"), //
	suffix_texi("application/x-texinfo"), //
	suffix_texinfo("application/x-texinfo"), //
	suffix_tgz("application/x-compressed"), //
	suffix_tif("image/tiff"), //
	suffix_tiff("image/tiff"), //
	suffix_tr("application/x-troff"), //
	suffix_trm("application/x-msterminal"), //
	suffix_tsv("text/tab-separated-values"), //
	suffix_txt("text/plain"), //
	suffix_uls("text/iuls"), //
	suffix_ustar("application/x-ustar"), //
	suffix_vcf("text/x-vcard"), //
	suffix_vrml("x-world/x-vrml"), //
	suffix_wav("audio/x-wav"), //
	suffix_wcm("application/vnd.ms-works"), //
	suffix_wdb("application/vnd.ms-works"), //
	suffix_wks("application/vnd.ms-works"), //
	suffix_wmf("application/x-msmetafile"), //
	suffix_wps("application/vnd.ms-works"), //
	suffix_wri("application/x-mswrite"), //
	suffix_wrl("x-world/x-vrml"), //
	suffix_wrz("x-world/x-vrml"), //
	suffix_xaf("x-world/x-vrml"), //
	suffix_xbm("image/x-xbitmap"), //
	suffix_xla("application/vnd.ms-excel"), //
	suffix_xlam("application/vnd.ms-excel.addin.macroEnabled.12"), //
	suffix_xlc("application/vnd.ms-excel"), //
	suffix_xlm("application/vnd.ms-excel"), //
	suffix_xls("application/vnd.ms-excel"), //
	suffix_xlsb("application/vnd.ms-excel.sheet.binary.macroEnabled.12"), //
	suffix_xlsx("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"), //
	suffix_xlt("application/vnd.ms-excel"), //
	suffix_xltx("application/vnd.openxmlformats-officedocument.spreadsheetml.template"), //
	suffix_xlw("application/vnd.ms-excel"), //
	suffix_xof("x-world/x-vrml"), //
	suffix_xpm("image/x-xpixmap"), //
	suffix_xwd("image/x-xwindowdump"), //
	suffix_z("application/x-compress"), //
	suffix_zip("application/zip"); //

	private String type;

	private MIMEType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
}
