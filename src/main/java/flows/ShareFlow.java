package flows;

import screens.RecipeDetailScreen;

public class ShareFlow {

    private final RecipeDetailScreen detail;

    public ShareFlow(RecipeDetailScreen detail) {
        this.detail = detail;
    }

    // ===== OPEN SHARE =====
    public void openShareDialog() {
        detail.clickShare();
    }

    // ===== SHARE ZALO =====
    public boolean shareViaZalo() {
        openShareDialog();
        return detail.shareViaZaloQuick();
    }

    // ===== SHARE MESSENGER =====
    public boolean shareViaMessenger() {
        openShareDialog();
        return detail.shareViaMessengerFullFlow();
    }

    // ===== SHARE GMAIL =====
    public boolean shareViaGmail() {
        openShareDialog();
        return detail.shareViaGmailFlow();
    }
}