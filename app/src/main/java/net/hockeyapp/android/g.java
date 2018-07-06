package net.hockeyapp.android;

import android.content.Context;
import android.text.TextUtils;

public class g {
    public static void initialize(Context context) {
        loadFromResources("hockeyapp_crash_dialog_title", 0, context);
        loadFromResources("hockeyapp_crash_dialog_message", 1, context);
        loadFromResources("hockeyapp_crash_dialog_negative_button", 2, context);
        loadFromResources("hockeyapp_crash_dialog_neutral_button", 3, context);
        loadFromResources("hockeyapp_crash_dialog_positive_button", 4, context);
        loadFromResources("hockeyapp_download_failed_dialog_title", 256, context);
        loadFromResources("hockeyapp_download_failed_dialog_message", k.DOWNLOAD_FAILED_DIALOG_MESSAGE_ID, context);
        loadFromResources("hockeyapp_download_failed_dialog_negative_button", k.DOWNLOAD_FAILED_DIALOG_NEGATIVE_BUTTON_ID, context);
        loadFromResources("hockeyapp_download_failed_dialog_positive_button", k.DOWNLOAD_FAILED_DIALOG_POSITIVE_BUTTON_ID, context);
        loadFromResources("hockeyapp_update_mandatory_toast", 512, context);
        loadFromResources("hockeyapp_update_dialog_title", k.UPDATE_DIALOG_TITLE_ID, context);
        loadFromResources("hockeyapp_update_dialog_message", k.UPDATE_DIALOG_MESSAGE_ID, context);
        loadFromResources("hockeyapp_update_dialog_negative_button", k.UPDATE_DIALOG_NEGATIVE_BUTTON_ID, context);
        loadFromResources("hockeyapp_update_dialog_positive_button", k.UPDATE_DIALOG_POSITIVE_BUTTON_ID, context);
        loadFromResources("hockeyapp_expiry_info_title", k.EXPIRY_INFO_TITLE_ID, context);
        loadFromResources("hockeyapp_expiry_info_text", k.EXPIRY_INFO_TEXT_ID, context);
        loadFromResources("hockeyapp_feedback_failed_title", k.FEEDBACK_FAILED_TITLE_ID, context);
        loadFromResources("hockeyapp_feedback_failed_text", k.FEEDBACK_FAILED_TEXT_ID, context);
        loadFromResources("hockeyapp_feedback_name_hint", k.FEEDBACK_NAME_INPUT_HINT_ID, context);
        loadFromResources("hockeyapp_feedback_email_hint", k.FEEDBACK_EMAIL_INPUT_HINT_ID, context);
        loadFromResources("hockeyapp_feedback_subject_hint", k.FEEDBACK_SUBJECT_INPUT_HINT_ID, context);
        loadFromResources("hockeyapp_feedback_message_hint", k.FEEDBACK_MESSAGE_INPUT_HINT_ID, context);
        loadFromResources("hockeyapp_feedback_last_updated_text", k.FEEDBACK_LAST_UPDATED_TEXT_ID, context);
        loadFromResources("hockeyapp_feedback_attachment_button_text", k.FEEDBACK_ATTACHMENT_BUTTON_TEXT_ID, context);
        loadFromResources("hockeyapp_feedback_send_button_text", k.FEEDBACK_SEND_BUTTON_TEXT_ID, context);
        loadFromResources("hockeyapp_feedback_response_button_text", k.FEEDBACK_RESPONSE_BUTTON_TEXT_ID, context);
        loadFromResources("hockeyapp_feedback_refresh_button_text", k.FEEDBACK_REFRESH_BUTTON_TEXT_ID, context);
        loadFromResources("hockeyapp_feedback_title", k.FEEDBACK_TITLE_ID, context);
        loadFromResources("hockeyapp_feedback_send_generic_error", k.FEEDBACK_SEND_GENERIC_ERROR_ID, context);
        loadFromResources("hockeyapp_feedback_send_network_error", k.FEEDBACK_SEND_NETWORK_ERROR_ID, context);
        loadFromResources("hockeyapp_feedback_validate_subject_error", k.FEEDBACK_VALIDATE_SUBJECT_ERROR_ID, context);
        loadFromResources("hockeyapp_feedback_validate_email_error", k.FEEDBACK_VALIDATE_EMAIL_ERROR_ID, context);
        loadFromResources("hockeyapp_feedback_validate_email_empty", k.FEEDBACK_VALIDATE_EMAIL_EMPTY_ID, context);
        loadFromResources("hockeyapp_feedback_validate_name_error", k.FEEDBACK_VALIDATE_NAME_ERROR_ID, context);
        loadFromResources("hockeyapp_feedback_validate_text_error", k.FEEDBACK_VALIDATE_TEXT_ERROR_ID, context);
        loadFromResources("hockeyapp_feedback_generic_error", k.FEEDBACK_GENERIC_ERROR_ID, context);
        loadFromResources("hockeyapp_feedback_attach_file", k.FEEDBACK_ATTACH_FILE_ID, context);
        loadFromResources("hockeyapp_feedback_attach_picture", k.FEEDBACK_ATTACH_PICTURE_ID, context);
        loadFromResources("hockeyapp_feedback_select_file", k.FEEDBACK_SELECT_FILE_ID, context);
        loadFromResources("hockeyapp_feedback_select_picture", k.FEEDBACK_SELECT_PICTURE_ID, context);
        loadFromResources("hockeyapp_feedback_max_attachments_allowed", k.FEEDBACK_MAX_ATTACHMENTS_ALLOWED, context);
        loadFromResources("hockeyapp_login_headline_text", k.LOGIN_HEADLINE_TEXT_ID, context);
        loadFromResources("hockeyapp_login_missing_credentials_toast", k.LOGIN_MISSING_CREDENTIALS_TOAST_ID, context);
        loadFromResources("hockeyapp_login_email_hint", k.LOGIN_EMAIL_INPUT_HINT_ID, context);
        loadFromResources("hockeyapp_login_password_hint", k.LOGIN_PASSWORD_INPUT_HINT_ID, context);
        loadFromResources("hockeyapp_login_login_button_text", k.LOGIN_LOGIN_BUTTON_TEXT_ID, context);
        loadFromResources("hockeyapp_paint_indicator_toast", k.PAINT_INDICATOR_TOAST_ID, context);
        loadFromResources("hockeyapp_paint_menu_save", k.PAINT_MENU_SAVE_ID, context);
        loadFromResources("hockeyapp_paint_menu_undo", k.PAINT_MENU_UNDO_ID, context);
        loadFromResources("hockeyapp_paint_menu_clear", k.PAINT_MENU_CLEAR_ID, context);
        loadFromResources("hockeyapp_paint_dialog_message", k.PAINT_DIALOG_MESSAGE_ID, context);
        loadFromResources("hockeyapp_paint_dialog_negative_button", k.PAINT_DIALOG_NEGATIVE_BUTTON_ID, context);
        loadFromResources("hockeyapp_paint_dialog_positive_button", k.PAINT_DIALOG_POSITIVE_BUTTON_ID, context);
        loadFromResources("hockeyapp_permission_update_title", k.PERMISSION_UPDATE_TITLE_ID, context);
        loadFromResources("hockeyapp_permission_update_message", k.PERMISSION_UPDATE_MESSAGE_ID, context);
        loadFromResources("hockeyapp_permission_dialog_negative_button", k.PERMISSION_DIALOG_NEGATIVE_BUTTON_ID, context);
        loadFromResources("hockeyapp_permission_dialog_positive_button", k.PERMISSION_DIALOG_POSITIVE_BUTTON_ID, context);
        loadFromResources("hockeyapp_dialog_positive_button", k.DIALOG_POSITIVE_BUTTON_ID, context);
        loadFromResources("hockeyapp_dialog_negative_button", k.DIALOG_NEGATIVE_BUTTON_ID, context);
        loadFromResources("hockeyapp_dialog_error_title", k.DIALOG_ERROR_TITLE_ID, context);
        loadFromResources("hockeyapp_dialog_error_message", k.DIALOG_ERROR_MESSAGE_ID, context);
    }

    private static void loadFromResources(String str, int i, Context context) {
        int identifier = context.getResources().getIdentifier(str, "string", context.getPackageName());
        if (identifier != 0) {
            Object string = context.getString(identifier);
            if (!TextUtils.isEmpty(string)) {
                k.set(i, string);
            }
        }
    }
}
