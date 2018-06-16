package cn.zhucongqi.oauth2.base.validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.jfinal.kit.StrKit;

import cn.zhucongqi.oauth2.base.clientcredentials.OAuthClientCredentials;
import cn.zhucongqi.oauth2.consts.OAuthConsts;
import cn.zhucongqi.oauth2.consts.OAuthError;
import cn.zhucongqi.oauth2.exception.OAuthProblemException;
import cn.zhucongqi.oauth2.kit.OAuthExceptionHandleKit;

/**
 * OAuthConsts Request Validator
 * @author Jobsz [zcq@zhucongqi.cn]
 * @version
 * @param <T>
 */
public abstract class OAuthValidator {

	private HttpServletRequest request = null;
	//request require parameter's name
    protected List<String> requiredParams = new ArrayList<String>();
    //request require parameter's default value
    protected HashMap<String, String> paramDefaultValues = new HashMap<String, String>();
    
    private OAuthClientCredentials clientCredentials = null;
    
    public OAuthValidator(HttpServletRequest request) {
    	this.request = request;
    	//default require scope and state
    	this.requiredParams.add(OAuthConsts.OAuth.OAUTH_SCOPE);
    	this.requiredParams.add(OAuthConsts.OAuth.OAUTH_STATE);
    	this.initRequiredParams();
        this.initParamDefaultValues();
    }

    
    /**
     * Set client request's credentials validator
     * @param clientCredentialsValidator
     */
    public void setClientCredentials(OAuthClientCredentials clientCredentials) {
    	this.clientCredentials = clientCredentials;
    }
    
    /**
     * init required params
     */
    public abstract void initRequiredParams();

    /**
     * init param default value  
     */
    public abstract void initParamDefaultValues();
    
    /**
     * validate method
     * @throws OAuthProblemException
     */
    private void validateMethod() throws OAuthProblemException {
        String method = this.request.getMethod();
        if (!method.equals(OAuthConsts.HttpMethod.GET) && !method.equals(OAuthConsts.HttpMethod.POST)) {
            throw OAuthProblemException.error(OAuthError.CodeResponse.INVALID_REQUEST)
                .description("Method not correct.");
        }
    }

    /**
     * validate content type
     * @throws OAuthProblemException
     */
    private void validateContentType() throws OAuthProblemException {
        String contentType = this.request.getContentType();
        final String expectedContentType = OAuthConsts.ContentType.URL_ENCODED;
        if (!OAuthExceptionHandleKit.hasContentType(contentType, expectedContentType)) {
            throw OAuthExceptionHandleKit.handleBadContentTypeException(expectedContentType);
        }
    }

    /**
     * validate require parameter
     * @throws OAuthProblemException
     */
    private void validateRequiredParameters() throws OAuthProblemException {
        final Set<String> missingParameters = new HashSet<String>();
        for (String requiredParam : this.requiredParams) {
            String val = this.request.getParameter(requiredParam);
            if (StrKit.isBlank(val)) {
                missingParameters.add(requiredParam);
            }
        }
        if (!missingParameters.isEmpty()) {
            throw OAuthExceptionHandleKit.handleMissingParameters(missingParameters);
        }
    }
    
    /**
     * validate parameter default values
     * @throws OAuthProblemException
     */
    private void validateParameterDefaultValues() throws OAuthProblemException {
    	final Set<String> keys = this.paramDefaultValues.keySet();
    	for (String key : keys) {
			String param = this.request.getParameter(key);
			String mustValue = this.paramDefaultValues.get(key);
			if (StrKit.isBlank(param) 
					|| (StrKit.notBlank(param) && !mustValue.equals(param))) {
				throw OAuthExceptionHandleKit.handleInvalidValueException(param, mustValue);
			}
		}
    }
    
    private String scope = "DEFAULT SCOPE";
    private String state = "DEFAULT STATE";
	private String clientId = "DEFAULT CLIENT_ID";
	private String clientSecret = "DEFAULT_CLIENT_SECRET";
	private String code = "DEFAULT_CODE";
    
    public String getScope() {
		return this.scope;
	}

	private void setScope(String scope) {
		this.scope = scope;
	}

	public String getState() {
		return this.state;
	}

	private void setState(String state) {
		this.state = state;
	}
	
	public String getClientId() {
		return this.clientId;
	}

	private void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return this.clientSecret;
	}

	private void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getCode() {
		return this.code;
	}

	private void setCode(String code) {
		this.code = code;
	}
	
	/**
     * init client parameters
     * @param request
     */
    private void initClientParameters() {
    	// client and secret
    	String clientId = this.request.getParameter(OAuthConsts.OAuth.OAUTH_CLIENT_ID);
    	if (StrKit.notBlank(clientId)) {
        	this.setClientId(clientId);	
		}
    	
    	String clientSecret = this.request.getParameter(OAuthConsts.OAuth.OAUTH_CLIENT_SECRET);
    	if (StrKit.notBlank(clientSecret)) {
        	this.setClientSecret(clientSecret);	
		}
    	
    	String code = this.request.getParameter(OAuthConsts.OAuth.OAUTH_CODE);
    	if (StrKit.notBlank(code)) {
        	this.setCode(code);	
		}
    	
    	String state = this.request.getParameter(OAuthConsts.OAuth.OAUTH_STATE);
		if (StrKit.notBlank(state)) {
			this.setState(state);
		}
		
		String scope = this.request.getParameter(OAuthConsts.OAuth.OAUTH_SCOPE);
		if (StrKit.notBlank(scope)) {
			this.setScope(scope);
		}
    }

    /**
     * validate request
     * 
     * @throws OAuthProblemException
     */
    public void validate() throws OAuthProblemException {
        this.validateContentType();
        this.validateMethod();
        this.validateRequiredParameters();
        this.validateParameterDefaultValues();
        
        this.initClientParameters();
		
		//client credentials validation
		this.clientCredentials.validateClientCredentials(this.request);
    }
}
