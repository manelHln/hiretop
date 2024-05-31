import Head from "next/head";

const Meta = ({ title, keywords, description }) => {
  return (
    <Head>
      <meta httpEquiv="X-UA-Compatible" content="IE=edge" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <meta name="keywords" content={keywords} />
      <meta name="description" content={description} />
      <link rel="icon" href="/favicon.ico" />
      <link rel="stylesheet" href="/assets/css/bootstrap.min.css" />
      <link rel="stylesheet" href="/assets/css/LineIcons.2.0.css" />
      <link rel="stylesheet" href="/assets/css/animate.css" />
      <link rel="stylesheet" href="/assets/css/tiny-slider.css" />
      <link rel="stylesheet" href="/assets/css/glightbox.min.css" />
      <link rel="stylesheet" href="/assets/css/main.css" />
      <title>{title}</title>
    </Head>
  );
};

Meta.defaultProps = {
  title: "Hiretop",
  keywords: "Hiretop",
  description: "Find your dream job around the world",
};
export default Meta;
