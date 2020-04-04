/**
 * 
 */
package vn.com.fobelife.common.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ahuynh
 *
 */
@Getter
@Setter
@Builder
public class ResultInfo {
    
    private String status;
    private String message;
    private Object data;

}
