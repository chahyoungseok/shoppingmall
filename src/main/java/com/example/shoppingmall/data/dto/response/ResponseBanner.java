package com.example.shoppingmall.data.dto.response;

import com.example.shoppingmall.data.entity.Banner;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseBanner {
   private String imgKey;

   @Builder
   public ResponseBanner(Banner banner) {
      this.imgKey = banner.getImgKey();
   }
}
