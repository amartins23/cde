/*!
 * Copyright 2018 Webdetails, a Hitachi Vantara company. All rights reserved.
 *
 * This software was developed by Webdetails and is provided under the terms
 * of the Mozilla Public License, Version 2.0, or any later version. You may not use
 * this file except in compliance with the license. If you need a copy of the license,
 * please go to http://mozilla.org/MPL/2.0/. The Initial Developer is Webdetails.
 *
 * Software distributed under the Mozilla Public License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. Please refer to
 * the license for the specific language governing your rights and limitations.
 */
package org.pentaho.ctools.cde.environment;

import java.util.Locale;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pentaho.ctools.cde.extapi.CdeApiPathProvider;
import org.pentaho.ctools.cde.plugin.resource.PluginResourceLocationManager;
import pt.webdetails.cdf.dd.IPluginResourceLocationManager;
import pt.webdetails.cdf.dd.InitializationException;
import pt.webdetails.cdf.dd.datasources.IDataSourceManager;
import pt.webdetails.cdf.dd.extapi.ICdeApiPathProvider;
import pt.webdetails.cdf.dd.extapi.IFileHandler;
import pt.webdetails.cdf.dd.model.core.writer.IThingWriterFactory;
import pt.webdetails.cdf.dd.model.inst.Dashboard;
import pt.webdetails.cdf.dd.model.inst.writer.cdfrunjs.dashboard.CdfRunJsDashboardWriteContext;
import pt.webdetails.cdf.dd.model.inst.writer.cdfrunjs.dashboard.CdfRunJsDashboardWriteOptions;
import pt.webdetails.cdf.dd.model.inst.writer.cdfrunjs.dashboard.legacy.PentahoCdfRunJsDashboardWriteContext;
import pt.webdetails.cdf.dd.util.Utils;
import pt.webdetails.cpf.PluginEnvironment;
import pt.webdetails.cpf.bean.IBeanFactory;
import pt.webdetails.cpf.context.api.IUrlProvider;
import pt.webdetails.cpf.api.IContentAccessFactoryExtended;
import pt.webdetails.cpf.repository.api.IBasicFile;
import pt.webdetails.cpf.repository.api.IRWAccess;
import pt.webdetails.cpf.repository.api.IReadAccess;
import pt.webdetails.cpf.resources.IResourceLoader;
import pt.webdetails.cpf.session.IUserSession;

public class CdeEnvironment implements ICdeEnvironmentExtended {

  private static final Log logger = LogFactory.getLog( CdeEnvironment.class );
  private static final String PLUGIN_REPOSITORY_DIR = "/public/cde";
  private static final String CDE_XML = "cde.xml";
  private static final String SYSTEM_DIR = "system";
  private static final String PLUGIN = "plugin";
  private static final String DEFAULT_PLUGIN_ID = "cdf";

  private static final String TYPE_BLUEPRINT = "blueprint";
  private static final String SCHEMA_HTTP = "http";

  /* CDE Editor POC - Spike BACKLOG 24374 */
  private static final String DEFAULT_APPLICATION_ID = "/@pentaho/dependencies/1.0/";

  private IPluginResourceLocationManager pluginResourceLocationManager;
  private IContentAccessFactoryExtended contentAccessFactory;
  private IDataSourceManager dataSourceManager;
  private IFileHandler fileHandler;

  public CdeEnvironment() {
    pluginResourceLocationManager = new PluginResourceLocationManager();
  }

  @Override
  public void init( IBeanFactory factory ) throws InitializationException {
    logger.fatal( "init() - Not implemented for the OSGi environment" );
  }

  @Override
  public Locale getLocale() {
    logger.fatal( "getLocale() - Not implemented for the OSGi environment, using default EN" );

    /* CDE Editor POC - Spike BACKLOG 24374 */
    return Locale.ENGLISH;
  }

  @Override
  public IResourceLoader getResourceLoader() {
    logger.fatal( "getResourceLoader() - Not implemented for the OSGi environment" );
    return null;
  }

  @Override
  public String getCdfIncludes( String dashboard, String type, boolean debug, boolean absolute, String absRoot,
                                String scheme ) throws Exception {
    logger.fatal( "getCdfIncludes() - Not implemented for the OSGi environment, using local string instead" );

    if ( type != null && type.equals( TYPE_BLUEPRINT ) && scheme != null && scheme.equals( SCHEMA_HTTP )) {
      /* CDE Editor POC - Spike BACKLOG 24374 */
      return "\t<!-- cdf-blueprint-script-includes -->\n"
              + "\t<script language=\"javascript\" type=\"text/javascript\" "
              + "src=\"/@pentaho/dependencies/1.0/cdf/js/cdf-blueprint-script-includes.js\"></script>\n"
              + "\t<!-- cdf-blueprint-style-includes -->\n"
              + "\t<link href=\"/@pentaho/dependencies/1.0/cdf/css/cdf-blueprint-style-includes.css\" rel=\"stylesheet\" "
              + "type=\"text/css\" />\n"
              + "\t<!-- cdf-blueprint-ie8style-includes -->\n"
              + "\t<!--[if lte IE 8]>\n"
              + "\t  <link href=\"/@pentaho/dependencies/1.0/cdf/css-legacy/blueprint/ie.css\" "
              + "rel=\"stylesheet\" type=\"text/css\" />\n"
              + "\t<![endif]-->\n"
              + "\t<link href=\"/@pentaho/dependencies/1.0/cdf/css/styles.css\" rel=\"stylesheet\" type=\"text/css\" />\n"
              + "\n";
    }

    return null;
  }

  @Override
  public ICdeApiPathProvider getExtApi() {
    return new CdeApiPathProvider( this.getUrlProvider() );
  }

  @Override
  public IFileHandler getFileHandler() {
    return this.fileHandler;
  }

  public void setFileHandler(IFileHandler fileHandler){
    this.fileHandler = fileHandler;
  }

  @Override
  public IUrlProvider getUrlProvider() {
    logger.fatal( "getUrlProvider() - Not implemented for the OSGi environment" );
    return null;
  }

  @Override
  public IUserSession getUserSession() {
    logger.fatal( "getUserSession() - Not implemented for the OSGi environment" );
    return null;
  }

  @Override
  public void refresh() {
    logger.fatal( "refresh() - Not implemented for the OSGi environment" );
  }

  @Override
  public String getApplicationBaseUrl() {
    return "";
  }

  @Override
  public String getApplicationReposUrl() {
    logger.fatal( String.format( "getApplicationReposUrl() - Not implemented for the OSGi environment, using '%s'.", DEFAULT_APPLICATION_ID ) );
    return DEFAULT_APPLICATION_ID;
  }

  @Override
  public IDataSourceManager getDataSourceManager() {
    return this.dataSourceManager;
  }

  public void setDataSourceManager( IDataSourceManager dataSourceManager ) {
    this.dataSourceManager = dataSourceManager;
  }

  @Override
  public IPluginResourceLocationManager getPluginResourceLocationManager() {
    return pluginResourceLocationManager;
  }

  public void setPluginResourceLocationManager( IPluginResourceLocationManager pluginResourceLocationManager ) {
    this.pluginResourceLocationManager = pluginResourceLocationManager;
  }

  @Override
  public String getPluginRepositoryDir() {
    return PLUGIN_REPOSITORY_DIR;
  }

  @Override
  public String getPluginId() {
    return DEFAULT_PLUGIN_ID; // TODO: any reason to keep supporting???
  }

  @Override
  public PluginEnvironment getPluginEnv() {
    return null;
  }

  @Override
  public String getSystemDir() {
    return SYSTEM_DIR;
  }

  @Override
  public String getApplicationBaseContentUrl() {
    return Utils.joinPath( getApplicationBaseUrl(), PLUGIN, getPluginId() ) + "/";
  }

  @Override
  public String getRepositoryBaseContentUrl() {
    return Utils.joinPath( getApplicationBaseUrl(), PLUGIN, getPluginId() ) + "/res/"; // TODO: review for osgi, deprecate ???
  }

  @Override
  public CdfRunJsDashboardWriteContext getCdfRunJsDashboardWriteContext( IThingWriterFactory factory, String indent,
                                                                         boolean bypassCacheRead, Dashboard dash,
                                                                         CdfRunJsDashboardWriteOptions options ) {
    if ( dash.getWcdf().isRequire() ) {
      return new pt.webdetails.cdf.dd.model.inst.writer.cdfrunjs.dashboard.amd.PentahoCdfRunJsDashboardWriteContext(
          factory, indent, bypassCacheRead, dash, options );
    } else {
      return new PentahoCdfRunJsDashboardWriteContext( factory, indent, bypassCacheRead, dash, options );
    }
  }

  @Override
  public CdfRunJsDashboardWriteContext getCdfRunJsDashboardWriteContext( CdfRunJsDashboardWriteContext factory,
                                                                         String indent ) {
    return new PentahoCdfRunJsDashboardWriteContext( factory, indent );
  }

  @Override
  public IBasicFile getCdeXml() {
    if ( getContentAccessFactory().getUserContentAccess( "/" )
        .fileExists( PLUGIN_REPOSITORY_DIR + "/" + CDE_XML ) ) {
      return getContentAccessFactory().getUserContentAccess( "/" )
        .fetchFile( PLUGIN_REPOSITORY_DIR + "/" + CDE_XML );
    } else if ( getContentAccessFactory().getPluginSystemReader( null ).fileExists( CDE_XML ) ) {
      return getContentAccessFactory().getPluginSystemReader( null ).fetchFile( CDE_XML );
    }
    return null;
  }

  @Override
  public boolean canCreateContent() {
    return false;
  }

  @Override
  public IContentAccessFactoryExtended getContentAccessFactory() {
    return this.contentAccessFactory;
  }

  public void setContentAccessFactory( IContentAccessFactoryExtended contentAccessFactory ) {
    this.contentAccessFactory = contentAccessFactory;
  }

  @Override
  public IReadAccess getPluginSystemReader(){ return getContentAccessFactory().getPluginSystemReader( null ); }

  @Override
  public IReadAccess getPluginSystemReader( String path ){ return getContentAccessFactory().getPluginSystemReader( path ); }

  @Override
  public IReadAccess getPluginRepositoryReader(){ return getContentAccessFactory().getPluginRepositoryReader( PLUGIN_REPOSITORY_DIR ); }

  @Override
  public IRWAccess getPluginSystemWriter(){ return getContentAccessFactory().getPluginSystemWriter( null ); }
}
